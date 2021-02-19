package ipoteka_calculator_database;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IpotekaMainTest {
    public static void main(String[] args) {
        String name;
        String surname;
        String gender;
        LocalDate birthday;
        int ipotekaUsulu;
        int tikintiIli;
        int verilmeUsulu;
        BigDecimal menzilDeyeri;
        int muddet;

        System.out.println("    \033[0;1m**********İpoteka    Kalkulyatoru**********\033[0;0m");
        System.out.println("       ****İpoteka krediti hesablanması****");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Adınız: ");
        name = scanner.nextLine();
        System.out.print("Soyadınız: ");
        surname = scanner.nextLine();
        System.out.print("Cinsiyyətiniz (K və ya Q yazın): ");
        gender = scanner.nextLine().toLowerCase();
        System.out.print("Doğum tarixiniz (Məs:21.05.2001) : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        birthday = LocalDate.parse(scanner.nextLine(), formatter);

        try {
            CustomerTest customerTest = CustomerTest.getInstance();
            customerTest.setId(1);
            customerTest.setName(name);
            customerTest.setSurname(surname);
            customerTest.setGender(gender);
            customerTest.setBirthday(birthday);

            ipotekaUsulu = Utility.ipoteUsulu();
            tikintiIli = Utility.tikintiIli();
            verilmeUsulu = Utility.verilmeUsulu();

            IpotekaUtilTest ipotekaUtil = IpotekaUtilTest.getInstance();
            ipotekaUtil.setId(1);
            ipotekaUtil.setIpotekaUsulu(ipotekaUsulu);
            ipotekaUtil.setTikintiIli(tikintiIli);
            ipotekaUtil.setVerilmeUsulu(verilmeUsulu);

            menzilDeyeri = Utility.menzilDeyeri();
            muddet = Utility.muddet();

            CreditTest creditTest = CreditTest.getInstance();
            creditTest.setId(1);
            creditTest.setMenzilinDeyeri(menzilDeyeri);
            creditTest.setMuddet(muddet);


            IpotekaSystem is = new IpotekaSystem();
            is.apply(customerTest, ipotekaUtil, creditTest);

            System.out.printf("Id: %s\nAdi: %s\nSoyadi: %s\nDoğum Tarixi: %s\nCinsi: %s\n",
                    customerTest.getId(),
                    customerTest.getName(),
                    customerTest.getSurname(),
                    customerTest.getBirthday(),
                    customerTest.getGender()

            );
            MonthlyPaymentTest monthlyPayment = MonthlyPaymentTest.getInstance();
            System.out.println("Bank Terefinden Verilen Kreditin Meblegi: " + creditTest.getKreditMeblegi() + "AZN");
            System.out.println("Ilkin Odenis Meblegi: " + creditTest.getIlkinOdenis() + "AZN");
            System.out.println("Illik Faiz Miqdari: " + creditTest.getIllikFaiz() + "%");
            System.out.println("Umumi Məbləg: " + creditTest.getFaizMeblegi().setScale(2, BigDecimal.ROUND_UP) + "AZN");

            System.out.println("Ayliq Odenecek Mebleg: " + monthlyPayment.getUmumiMebleg().setScale(2, BigDecimal.ROUND_UP) + "AZN");
            System.out.println("Base Ammount: " + monthlyPayment.getEsasMebleg().setScale(2, BigDecimal.ROUND_UP) + "AZN");
            System.out.println("Interes Ammount: " + monthlyPayment.getFaizMeblegi().setScale(2, BigDecimal.ROUND_UP) + "AZN");

            IpotekaJDBC.mainDatabase();
        } catch (AgeNotEnoughException e) {
            System.out.println("AgeNotEnoughException");
            e.printStackTrace();
        } catch (GenderException e) {
            System.out.println("GenderException");
            e.printStackTrace();
        }


    }
}
