package ipoteka_calculator_database;

import java.util.Date;

public class AgeNotEnoughException extends IpotekaSystemException {
    private char gender;
    private Date birthday;

    public AgeNotEnoughException(String message, long id) {
        super(message, id);
    }

    public AgeNotEnoughException(String message, long id, char gender, Date birthday) {
        super(message, id);
        this.gender = gender;
        this.birthday = birthday;
    }

}
