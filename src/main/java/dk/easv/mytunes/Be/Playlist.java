package dk.easv.mytunes.Be;


public class Playlist {

    private int id;
    private String name;
    private int numberOfSongs = 0;
    private int playTime = 0; //seconds
    private String formattedTime;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Playlist(int id, String name, int numberOfSongs, int playTime) {
        this.id = id;
        this.name = name;
        this.numberOfSongs = numberOfSongs;
        this.playTime = playTime;
        this.formattedTime = getFormattedTime();
    }

    public String getFormattedTime() {
        int minutes = playTime / 60;
        int seconds = playTime % 60;
        return String.format("%d.%02d", minutes, seconds);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
