package GUI;

import Game.Cell;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class GuiBoard extends GridPane {

    private Cell[][] board;
    private int size = 8;

//    public GuiBoard() {
//        System.out.print("me");
//        board = new Cell[size][];
//        FXMLLoader fxmlLoader = new
//                FXMLLoader(getClass().getResource("guiBoard.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public void draw() {
//        this.getChildren().clear();
//
//        int height = (int)this.getPrefHeight();
//        int width = (int)this.getPrefWidth();
//
//        int cellHeight = height / board.length;
//        int cellWidth = width / board[0].length;
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                    this.add(new Rectangle(cellWidth, cellHeight,
//                            Color.SLATEGRAY), j, i);
//            }
//        }
//    }
}
