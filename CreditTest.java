package ipoteka_calculator_database;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CreditTest {
    private static CreditTest creditTest;
    private long id;
    private long customerId;
    private BigDecimal menzilinDeyeri;
    private BigDecimal ilkinOdenis;
    private BigDecimal kreditMeblegi;
    private BigDecimal faizMeblegi;
    private LocalDate actionDate;
    private int muddet;
    private double illikFaiz;

    public CreditTest() {
    }

    public static CreditTest getInstance() {
        if (creditTest == null) {
            creditTest = new CreditTest();
        }
        return creditTest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getMenzilinDeyeri() {
        return menzilinDeyeri;
    }

    public void setMenzilinDeyeri(BigDecimal menzilinDeyeri) {
        this.menzilinDeyeri = menzilinDeyeri;
    }

    public BigDecimal getIlkinOdenis() {
        return ilkinOdenis;
    }

    public void setIlkinOdenis(BigDecimal ilkinOdenis) {
        this.ilkinOdenis = ilkinOdenis;
    }

    public BigDecimal getKreditMeblegi() {
        return kreditMeblegi;
    }

    public void setKreditMeblegi(BigDecimal kreditMeblegi) {
        this.kreditMeblegi = kreditMeblegi;
    }

    public int getMuddet() {
        return muddet;
    }

    public void setMuddet(int muddet) {
        this.muddet = muddet;
    }

    public double getIllikFaiz() {
        return illikFaiz;
    }

    public void setIllikFaiz(double illikFaiz) {
        this.illikFaiz = illikFaiz;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getFaizMeblegi() {
        return faizMeblegi;
    }

    public void setFaizMeblegi(BigDecimal faizMeblegi) {
        this.faizMeblegi = faizMeblegi;
    }

    public LocalDate getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }
}
