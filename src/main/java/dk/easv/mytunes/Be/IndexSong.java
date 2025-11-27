package dk.easv.mytunes.Be;

public class IndexSong {
    private Song song;
    private int index;

    public IndexSong(Song song, int index) {
        this.index = index;
        this.song = song;
    }
    public Song getSong() {
        return song;
    }
    public void setSong(Song song) {
        this.song = song;
    }
    public int getIndex() {
        return index;
    }
    public String getTitle() {
        return song.getTitle();
    }
    public int getId() {
        return song.getId();
    }
}
