package GUI;

import Game.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    protected void startGame() {
//        try {
//            HBox root = FXMLLoader.load(getClass().getResource("guiBoard.fxml"));
//            Scene scene = new Scene(root,520,400);
////            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//            Stage stage = new Stage();
//            stage.setTitle("Reversi");
//            stage.setScene(scene);
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
        Start s = new Start();
        s.init();
    }

    @FXML
    protected void openSettings() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reversi");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
