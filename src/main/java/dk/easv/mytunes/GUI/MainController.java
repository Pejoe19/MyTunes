package dk.easv.mytunes.GUI;

import dk.easv.mytunes.BLL.MusicException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML private TableView TvPlaylists;
    @FXML private TableColumn tblCoPLName;
    @FXML private TableColumn tblCoPLSongs;
    @FXML private TableColumn tblCoPLTime;
    @FXML private TableView tvSongs;
    @FXML private TableColumn tblCoTitle;
    @FXML private TableColumn tblCoArtist;
    @FXML private TableColumn tblCoTitle1;
    @FXML private TableColumn tblCoTime;

    private final Model model = new Model();


    public MainController() {
    }

    public void initialize(){
        loadSongs();
        loadPlaylists();
    }

    private void loadSongs() {
        tblCoTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblCoArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tblCoTitle1.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblCoTime.setCellValueFactory(new PropertyValueFactory<>("formattedTime"));

        try {
            tvSongs.setItems(model.loadSongs());
        } catch (MusicException e) {
            e.printStackTrace();
        }
    }

    private void loadPlaylists(){
        // Tells the table which properties of the playlist to show in which columns
        tblCoPLName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCoPLSongs.setCellValueFactory(new PropertyValueFactory<>("numberOfSongs"));
        tblCoPLTime.setCellValueFactory(new PropertyValueFactory<>("playTime"));
        try {
            // Gets the data from model
            TvPlaylists.setItems(model.loadPlaylists());
        } catch (MusicException e) {
            throw new RuntimeException(e);
        }
    }


}
