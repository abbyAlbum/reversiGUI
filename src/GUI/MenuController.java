package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    protected void startGame() {

    }

    @FXML
    protected void openSettings(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reversi");
            stage.show();
            //menu.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
