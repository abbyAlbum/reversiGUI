package GUI;

import Game.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class GuiBoard extends GridPane {

    private Board board;
    private Color color1;
    private Color color2;


    public GuiBoard(Board board, Color colour1, Color colour2) {
        this.color1 = colour1;
        this.color2 = colour2;
        this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("guiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

    }

    public void draw() {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();

        int cellHeight = height / board.getSize();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rectangle = new Rectangle(cellHeight, cellHeight, Color.SLATEGRAY);
                rectangle.setStroke(Color.BLACK);
                this.add(rectangle, j, i);
                Circle circle = new Circle(cellHeight, cellHeight, cellHeight/2 -2);
                if (board.getBoard_()[i][j].getValue() == 'X') {
                    circle.setFill(color1);
                    this.add(circle, j, i);
                }

                if (board.getBoard_()[i][j].getValue() == 'O')  {
                    circle.setFill(color2);
                    this.add(circle, j, i);
                }

            }
        }
    }
}
