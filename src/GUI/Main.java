package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {


    /**
     * Starts the game
     * @param primaryStage - the first stage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 600, 400);
            // TODO CREATE AN APPLICATION.CSS
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * the main - launches the game
     * @param args - the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
