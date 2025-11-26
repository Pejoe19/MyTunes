package dk.easv.mytunes.Be;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String category;
    private int time; // seconds
    private String filePath;

    public Song(int id, String title, String artist, String category, int time, String filePath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.time = time;
        this.filePath = filePath;
    }

    public String getFormattedTime() {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%d.%02d", minutes, seconds);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getCategory() { return category; }
    public int getTime() { return time; }
    public String getFilePath() { return filePath; }
}
