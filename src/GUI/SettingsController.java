package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class SettingsController {
    @FXML
    private RadioButton firstPlayer;
    @FXML
    private RadioButton secondPlayer;
    @FXML
    private ColorPicker colorOne;
    @FXML
    private ColorPicker colorTwo;
    @FXML
    private Slider size;
    @FXML
    private Button save;
    @FXML
    private Button back;

    private String playerOne;
    private String firstColor;
    private String secondColor;
    private String boardSize;

    /**
     * starts the game from the settings screen.
     */
    @FXML
    protected void save() {
        try {
            writeSettings();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * back option from settings, closes the window
     */
    @FXML
    protected void back() {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    /**
     * writes the settings from the text fields to settings.txt
     */
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

    /**
     * sets the strings to write into settings.txt
     */
    private void setStrings() {
        if (firstPlayer.isSelected())
            playerOne = firstPlayer.getText();
        else if (secondPlayer.isSelected())
            playerOne = secondPlayer.getText();
        else
            playerOne = "";
        firstColor = "#" + Integer.toHexString(colorOne.getValue().hashCode());
        secondColor = "#" + Integer.toHexString(colorTwo.getValue().hashCode());
        boardSize = Integer.toString((int) size.getValue());
    }
}
