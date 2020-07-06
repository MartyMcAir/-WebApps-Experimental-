package pkg1.hierarchy;

public class PopMusic implements Music{
    private PopMusic() {
    }

    // фабричный метод
    public static PopMusic getPopMusic() {
        return new PopMusic();
    }

    public String getSong() {
        return "Pop song..";
    }
}
