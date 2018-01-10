package Game;

import javafx.scene.paint.Color;

import java.util.List;

public class HumanPlayer implements Player {

    private char symbol_;
    private Color color;

    /**
    * constructor for Game.HumanPlayer.
    * @param symbol
    */
    public HumanPlayer(char symbol, Color c) {
        symbol_ = symbol;
        color = c;
    }

    /**
     * lets the player to make a move
     * @param moves possible moves for the player
     * @return the player's choice
     */
    public Point makeMove(List<Point> moves, Point p) {
        if (moves.isEmpty()) {
            return new Point(-1, -1);
        }
        ComparePoint cp = new ComparePoint();
        while (true) {
            Boolean isInMoves = false;
            for (int i = 0; i < moves.size(); ++i) {
                if (cp.compare(p, moves.get(i)) == 0) {
                    isInMoves = true;
                    break;
                } else isInMoves = false;
            }
            if (isInMoves) break;
        }
        return p;
    }

    /**
     * gets the player's symbol
     * @return symbol
     */
    public char getSymbol() { return symbol_; }

    /**
     * gets the player's color
     * @return color
     */
    public Color getColor() {
        return color;
    }
}
