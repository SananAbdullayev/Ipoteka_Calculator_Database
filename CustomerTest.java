package ipoteka_calculator_database;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomerTest {
    private static CustomerTest customerTest;
    private long id;
    private String name;
    private String surname;
    private char gender;
    private LocalDate birthday;

    public static CustomerTest getInstance() {
        if (customerTest == null) {
            customerTest = new CustomerTest();
        }
        return customerTest;
    }

    public CustomerTest() {
        this.id = 0;
        this.name = "";
        this.surname = "";
        this.gender = ' ';
        this.birthday = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.toLowerCase().charAt(0);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
