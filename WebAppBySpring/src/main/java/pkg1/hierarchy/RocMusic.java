package pkg1.hierarchy;

public class RocMusic implements Music {
    String songName = "Wing cries Mary";

    @Override
    public String getSong() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    private void doMyInit() {
        System.out.println("Doing my initialization");
    }

    private void doMyDestroy() {
        System.out.println("Doing my destruction");
    }
}
