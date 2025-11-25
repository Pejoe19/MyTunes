package dk.easv.mytunes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void clickOpenNewPL(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewPlaylist.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 240);
        Stage stage = new Stage();
        stage.setTitle("New Playlist in MyTunes");
        stage.setScene(scene);
        stage.show();
    }
}
