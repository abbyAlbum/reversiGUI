package GUI;

import Game.Board;
import Game.SettingsReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable{



    @FXML
    private HBox root;

    public BoardController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SettingsReader sr = new SettingsReader("./src/Settings.txt");
        sr.readFile();
        GuiBoard gb = new GuiBoard(new Board(sr.getSize()), sr.getColourOne(), sr.getColourTwo());
        gb.setPrefWidth(600);
        gb.setPrefHeight(400);
        root.getChildren().add(0, gb);
        gb.draw();
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            gb.setPrefWidth(boardNewWidth);
            gb.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            gb.setPrefHeight(newValue.doubleValue());
            gb.draw();
        });
    }
}
