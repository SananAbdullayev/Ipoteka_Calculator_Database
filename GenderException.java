package ipoteka_calculator_database;

public class GenderException extends IpotekaSystemException {
    private char gender;

    public GenderException(String message, long id) {
        super(message, id);
    }
    public GenderException(String message, long id, char gender) {
        super(message, id);
        this.gender = gender;
    }

}
