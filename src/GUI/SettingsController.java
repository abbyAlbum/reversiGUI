package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class SettingsController {
    @FXML
    private TextField firstPlayer;
    @FXML
    private TextField colorOne;
    @FXML
    private TextField colorTwo;
    @FXML
    private TextField size;
    private String playerOne;
    private String firstColor;
    private String secondColor;
    private String boardSize;

    @FXML
    protected void startGame(ActionEvent event) {
        writeSettings();
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("game.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reversi");
            stage.show();
            //menu.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeSettings() {
        setStrings();
        try {
            String path = "./src/Settings.txt";
            File inFile = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(path));
            File tmpFile = new File("./src/temp.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("SETTINGS")) {
                    pw.println(line);
                    pw.println(playerOne);
                    pw.println(firstColor);
                    pw.println(secondColor);
                    pw.println(boardSize);
                    pw.flush();
                    break;
                } else {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            // Delete original file
            if (!inFile.delete())
                throw new Exception("couldn't delete file");
            if (!tmpFile.renameTo(inFile))
                throw new Exception("couldn't rename file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStrings() {
        if ((playerOne = firstPlayer.getText()) == null)
            playerOne = "";
        if ((firstColor = colorOne.getText()) == null)
            firstColor = "";
        if ((secondColor = colorTwo.getText()) == null)
            secondColor = "";
        if ((boardSize = size.getText()) == null)
            boardSize = "";
    }
}
