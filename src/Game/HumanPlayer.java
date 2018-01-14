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
        int x = p.getX() + 1;
        int y = p.getY() + 1;
        Point newP = new Point(x, y);
        boolean flag = true;
        if (moves.isEmpty()) {
            return new Point(-2, -2);
        }
        ComparePoint cp = new ComparePoint();

            for (int i = 0; i < moves.size(); i++) {
                if (cp.compare(newP, moves.get(i)) == 0) {
                    flag = false;
                    break;
                }
            }
        x = newP.getX() - 1;
        y = newP.getY() - 1;
        Point p1 = new Point(x, y);
            if(flag) return new Point(-1, -1);
            else return p1;
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
