package GUI;

import Game.SettingsReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class SettingsController {
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
    @FXML
    private Label message;

    private String firstColor;
    private String secondColor;
    private String boardSize;

    /**
     * starts the game from the settings screen.
     */
    @FXML
    protected void save() {
        try {
            int n = writeSettings();
            if (n == 0) return;
            Stage stage = (Stage) save.getScene().getWindow();
            AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Reversi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * back option from settings, closes the window
     */
    @FXML
    protected void back() {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Reversi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * writes the settings from the text fields to settings.txt
     */
    private int writeSettings() {
        setStrings();
        if (firstColor.equals(secondColor) || colorTwo.getValue() == Color.GREEN) {
            message.setText("Player two, pick another color");
            message.setTextFill(Color.RED);
            return 0;
        } else if (colorOne.getValue() == Color.GREEN) {
            message.setText("Player one, pick another color");
            message.setTextFill(Color.RED);
            return 0;
        }
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
        return 1;
    }

    /**
     * sets the strings to write into settings.txt
     */
    private void setStrings() {
        firstColor = "#" + Integer.toHexString(colorOne.getValue().hashCode());
        if (firstColor.equals("#ff"))
            firstColor = "black";
        secondColor = "#" + Integer.toHexString(colorTwo.getValue().hashCode());
        if (secondColor.equals("#ff"))
            secondColor = "black";
        boardSize = Integer.toString((int) size.getValue());
    }

    public  void initialize() {
        SettingsReader sr = new SettingsReader("./src/Settings.txt");
        sr.readFile();
        colorOne.setValue(sr.getColourOne());
        colorTwo.setValue(sr.getColourTwo());
        size.setValue(sr.getSize());
    }
}
