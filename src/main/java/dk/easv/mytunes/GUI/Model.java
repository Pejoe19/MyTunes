package dk.easv.mytunes.GUI;

import dk.easv.mytunes.BLL.Logic;
import dk.easv.mytunes.BLL.MusicException;
import dk.easv.mytunes.Be.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private final Logic logic = new Logic();
    private ObservableList<Playlist> playlists;


    public ObservableList<Playlist> loadPlaylists() throws MusicException {
        // Gets the data from logic
        playlists = FXCollections.observableList(logic.getPlaylists());
        return playlists;
    }
}
