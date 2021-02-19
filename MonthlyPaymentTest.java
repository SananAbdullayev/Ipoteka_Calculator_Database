package ipoteka_calculator_database;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class MonthlyPaymentTest {
    private static MonthlyPaymentTest monthlyPayment;
    private long id;
    private long creditId;
    private LocalDate paymentDate;
    private BigDecimal esasMebleg;
    private BigDecimal faizMeblegi;
    private BigDecimal umumiMebleg;

    public MonthlyPaymentTest() {
    }

    public static MonthlyPaymentTest getInstance() {
        if (monthlyPayment == null) {
            monthlyPayment = new MonthlyPaymentTest();
        }
        return monthlyPayment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getEsasMebleg() {
        return esasMebleg;
    }

    public void setEsasMebleg(BigDecimal esasMebleg) {
        this.esasMebleg = esasMebleg;
    }

    public BigDecimal getFaizMeblegi() {
        return faizMeblegi;
    }

    public void setFaizMeblegi(BigDecimal faizMeblegi) {
        this.faizMeblegi = faizMeblegi;
    }

    public BigDecimal getUmumiMebleg() {
        return umumiMebleg;
    }

    public void setUmumiMebleg(BigDecimal umumiMebleg) {
        this.umumiMebleg = umumiMebleg;
    }
}
