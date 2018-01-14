package GUI;

import Game.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.List;


public class GuiBoard extends GridPane {

    private Board board;
    private Color color1;
    private Color color2;

    /**
     * Creates the guiboard
     * @param board - the board
     * @param colour1 - the first colour
     * @param colour2 - the second colour
     */
    public GuiBoard(Board board, Color colour1, Color colour2) {
        this.color1 = colour1;
        this.color2 = colour2;
        this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("guiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


    }

    /**
     * Gets the board
     * @return - the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Draws the board
     */
    public void draw(Player p1, Player p2, Integer counter) {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        List<Point> moves;
        int cellHeight = height / board.getSize();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rectangle = new Rectangle(cellHeight, cellHeight, Color.GREEN);
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
        if (counter == 0)
            moves = new BasicLogic(board).getPossibleMoves(p1, p2);
        else
            moves = new BasicLogic(board).getPossibleMoves(p2, p1);
        for (Point p : moves) {
            int x = p.getX();
            int y = p.getY();
            Rectangle rectangle = new Rectangle(cellHeight, cellHeight, Color.GREEN.brighter());
            rectangle.setStroke(Color.BLACK);
            this.add(rectangle, y - 1, x - 1);
        }
    }
}





