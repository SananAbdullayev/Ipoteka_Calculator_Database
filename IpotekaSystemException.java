package ipoteka_calculator_database;

public class IpotekaSystemException extends Exception{
    protected long id;

    public IpotekaSystemException(String message, long id) {
        super(message);
        this.id = id;
    }
}
