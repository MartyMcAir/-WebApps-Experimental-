package pkgConf;

public class Smoothie {
    public Juice juice;
    public OrangeJuice orangeJuice;

    public void setOrangeJuice(OrangeJuice juice) {
        this.orangeJuice = juice;
    }

    public String getOrangeJuiceName() {
        return orangeJuice.getJuiceName();
    }
}
