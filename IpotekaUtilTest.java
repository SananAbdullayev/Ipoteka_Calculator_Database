package ipoteka_calculator_database;

public class IpotekaUtilTest {
    private static IpotekaUtilTest ipotekaUtilTest;
    private long id;
    private int ipotekaUsulu;
    private int tikintiIli;
    private int verilmeUsulu;

    public IpotekaUtilTest() {
    }

    public static IpotekaUtilTest getInstance() {
        if (ipotekaUtilTest == null) {
            ipotekaUtilTest = new IpotekaUtilTest();
        }
        return ipotekaUtilTest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIpotekaUsulu() {
        return ipotekaUsulu;
    }

    public void setIpotekaUsulu(int ipotekaUsulu) {
        this.ipotekaUsulu = ipotekaUsulu;
    }

    public int getTikintiIli() {
        return tikintiIli;
    }

    public void setTikintiIli(int tikintiIli) {
        this.tikintiIli = tikintiIli;
    }

    public int getVerilmeUsulu() {
        return verilmeUsulu;
    }

    public void setVerilmeUsulu(int verilmeUsulu) {
        this.verilmeUsulu = verilmeUsulu;
    }
}
