package ipoteka_calculator_database;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class IpotekaSystem {

    public boolean apply(CustomerTest customer, IpotekaUtilTest ipotekaUtil, CreditTest creditTest) throws AgeNotEnoughException, GenderException {
        boolean result = true;
        Calendar a = Calendar.getInstance();
        a.add(Calendar.MONTH, creditTest.getMuddet());
        Date nextDate = a.getTime();// indiki Zaman + Ipoteka Muddeti
        Date endDate;

        if (customer.getGender() == 'k') {
            Calendar b = Calendar.getInstance();
            b.add(Calendar.YEAR, 65);
            endDate = b.getTime();// indiki Zaman + k->65, q->62
        } else if (customer.getGender() == 'q') {
            Calendar b = Calendar.getInstance();
            b.add(Calendar.YEAR, 62);
            endDate = b.getTime();// indiki Zaman + k->65, q->62
        } else {
            //todo Exception
            throw new GenderException(
                    "Zəhmət olmasa cinsinizi düzgün daxil edin!!!",
                    customer.getId(),
                    customer.getGender()
            );
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(localDateToDate(customer.getBirthday()));
//        System.out.println(LocalDateTime.now().getYear() - calendar1.get(Calendar.YEAR));
        if (nextDate.compareTo(endDate) > 0 || LocalDateTime.now().getYear() - calendar1.get(Calendar.YEAR) < 18) {
            System.out.println("ipoteka verile bilmez");
            throw new AgeNotEnoughException(
                    "Yaşınız uyğun deyil",
                    customer.getId(),
                    customer.getGender(),
                    localDateToDate(customer.getBirthday())
            );
        }
        System.out.println("IPOTEKA Verile Biler");
        //todo Ilkin ödənişin hesablanması.
        //1. Tikinti üsulu = 1 - dirsə -> 30%
        //2. Tikinti üsulu = 2 - dirsə -> 15%
        int maxAmount;
        if (ipotekaUtil.getIpotekaUsulu() == 1) {
            maxAmount = 150000;
        } else {
            maxAmount = 100000;
        }
        if (ipotekaUtil.getTikintiIli() == 1) {
            double faiz = 0.3;
            if (creditTest.getMenzilinDeyeri().subtract(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz))).compareTo(BigDecimal.valueOf(maxAmount)) <= 0) {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz)));
            } else {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().subtract(BigDecimal.valueOf(maxAmount)));
            }
        } else if (ipotekaUtil.getTikintiIli() == 2) {
            double faiz = 0.15;
            if (creditTest.getMenzilinDeyeri().subtract(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz))).compareTo(BigDecimal.valueOf(maxAmount)) <= 0) {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz)));
            } else {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().subtract(BigDecimal.valueOf(maxAmount)));
            }
        }
        //todo Kredit Mebleginin hesablanmasi
        creditTest.setKreditMeblegi(creditTest.getMenzilinDeyeri().subtract(creditTest.getIlkinOdenis()));

        //todo Illik Faiz derecesinin hesablanmasi
        if (ipotekaUtil.getIpotekaUsulu() == 1) {
            if (ipotekaUtil.getVerilmeUsulu() == 1) {
                creditTest.setIllikFaiz(7);
            } else if (ipotekaUtil.getVerilmeUsulu() == 2) {
                creditTest.setIllikFaiz(8);
            }
        } else if (ipotekaUtil.getIpotekaUsulu() == 2) {
            if (ipotekaUtil.getVerilmeUsulu() == 1) {
                creditTest.setIllikFaiz(3.7);
            } else if (ipotekaUtil.getVerilmeUsulu() == 2) {
                creditTest.setIllikFaiz(4);
            }
        }
        MonthlyPaymentTest monthlyPayment = MonthlyPaymentTest.getInstance();
        //todo Ayliq Odenisin Tapilmasi -> Total Amount
        monthlyPayment.setUmumiMebleg((creditTest.getKreditMeblegi().multiply(BigDecimal.valueOf(creditTest.getIllikFaiz() / (12 * 100))).multiply(
                BigDecimal.valueOf(Math.pow((1 + (creditTest.getIllikFaiz() / (12 * 100))), creditTest.getMuddet())))).divide(
                BigDecimal.valueOf(Math.pow((1 + (creditTest.getIllikFaiz() / (12 * 100))), creditTest.getMuddet()) - 1), RoundingMode.HALF_UP));
        //todo Esas Meblegin hesablanmasi -> Base Amount
        monthlyPayment.setEsasMebleg(creditTest.getKreditMeblegi().divide(BigDecimal.valueOf(creditTest.getMuddet()), RoundingMode.HALF_UP));

        //todo Faiz Mebleginin hesablanmasi -> Interest Amount
        monthlyPayment.setFaizMeblegi(monthlyPayment.getUmumiMebleg().subtract(monthlyPayment.getEsasMebleg()));

        //todo Umumi Faiz Meblegi
        creditTest.setFaizMeblegi(monthlyPayment.getUmumiMebleg().multiply(BigDecimal.valueOf(creditTest.getMuddet())));

        creditTest.setActionDate(LocalDate.now());
        monthlyPayment.setPaymentDate(LocalDate.now().plusMonths(1));
        return result;
    }

    public static Date localDateToDate(LocalDate birthday) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(birthday.atStartOfDay(defaultZoneId).toInstant());
    }
}