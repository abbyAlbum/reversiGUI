package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button play;
    @FXML
    private Button settings;

    /**
     * Starts the playing of the game
     */
    @FXML
    protected void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader((ClassLoader.getSystemClassLoader().getResource("guiBoard.fxml")));
            HBox root = loader.load();
            Scene scene = new Scene(root,600,400);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Stage stage = (Stage) play.getScene().getWindow();
            stage.setTitle("Reversi");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * opens the settinfs
     */
    @FXML
    protected void openSettings() {
        try {
            FXMLLoader loader = new FXMLLoader((ClassLoader.getSystemClassLoader().getResource("settings.fxml")));
            AnchorPane root = loader.load();
            Stage stage = (Stage) settings.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Reversi");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
