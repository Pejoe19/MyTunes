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

    private final Model model = new Model();


    public MainController() {
    }


    public void initialize(){
        loadPlaylists();
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
