package pkg1.hierarchy;

public class MusicPlayer {
    private Music music;
    private int volume;
    private String songName;

    public MusicPlayer() {
    }

    // IoC
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongName() {
        return songName;
    }
}
