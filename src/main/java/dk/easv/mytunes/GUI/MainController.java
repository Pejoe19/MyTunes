package dk.easv.mytunes.GUI;

import dk.easv.mytunes.BLL.MusicException;
import dk.easv.mytunes.Be.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML private Button btnEditSong;
    @FXML private TableView TvPlaylists;
    @FXML private TableColumn tblCoPLName;
    @FXML private TableColumn tblCoPLSongs;
    @FXML private TableColumn tblCoPLTime;
    @FXML private TableView tvSongs;
    @FXML private TableColumn tblCoTitle;
    @FXML private TableColumn tblCoArtist;
    @FXML private TableColumn tblCoTitle1;
    @FXML private TableColumn tblCoTime;

    private Model model;

    {
        try {
            model = new Model();
        } catch (MusicException e) {
            displayError(e);
        }
    }

    private Song selectedSong;

    public MainController() {
    }

    public void initialize(){
        loadSongs();
        loadPlaylists();

        tvSongs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null){
                selectedSong = (Song) newValue;
                btnEditSong.setDisable(false);
            }
        });
    }

    private void loadSongs() {
        tblCoTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblCoArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tblCoTitle1.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblCoTime.setCellValueFactory(new PropertyValueFactory<>("formattedTime"));

        try {
            tvSongs.setItems(model.loadSongs());
        } catch (MusicException e) {
            displayError(e);
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
            displayError(e);
        }
    }


   @FXML
   private void editSong(ActionEvent actionEvent) {
       try {
           System.out.println(selectedSong.getId());
           openSongWindow("edit", selectedSong, actionEvent);


       } catch (MusicException | IOException e) {
           displayError(e);
       }
   }

    public void openSongWindow(String windowType, Song song, ActionEvent actionEvent) throws MusicException, IOException {
        // Loads the new fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dk/easv/mytunes/NewEditSong.fxml"));
        Scene scene = new Scene(loader.load());

        // Set this controller as a parent controller for the new controller
        SongController songController = loader.getController();
        songController.setParent(this);

        // If the window is used to edit a song, then setup editmode and load the data for the song
        if (windowType.equals("edit") && song != null){
            songController.setEditMode();
            songController.init(song);
        }

        Stage stage = new Stage();
        stage.setScene(scene);

        // Locks the old window while the new window is open
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setResizable(false);

        stage.show();
    }


    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void updateSong(Song song) {
        try {
            model.updateSong(song);
            tvSongs.getSelectionModel().select(song.getId()-1);
        } catch (Exception e) {
            displayError(e);
        }
    }
}
