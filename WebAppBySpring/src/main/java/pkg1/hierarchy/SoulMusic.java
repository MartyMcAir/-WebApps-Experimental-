package pkg1.hierarchy;

public class SoulMusic implements Music{
    public String getSong() {
        return "Soul Song";
    }

    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }
}
