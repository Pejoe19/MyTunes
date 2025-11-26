package dk.easv.mytunes.GUI;

import dk.easv.mytunes.BLL.Logic;
import dk.easv.mytunes.BLL.MusicException;
import dk.easv.mytunes.Be.Playlist;
import dk.easv.mytunes.Be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private final Logic logic = new Logic();
    private ObservableList<Song> songs;
    private ObservableList<Playlist> playlists;

    public ObservableList<Song> loadSongs() throws MusicException {
        songs = FXCollections.observableArrayList(logic.getSongs());
        return songs;
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public ObservableList<Playlist> loadPlaylists() throws MusicException {
        // Gets the data from logic
        playlists = FXCollections.observableList(logic.getPlaylists());
        return playlists;
    }
}
