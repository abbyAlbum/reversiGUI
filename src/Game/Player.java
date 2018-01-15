package Game;

import java.util.List;

public interface Player {

    /**
     * Makes the players move
     * @param possibleMoves - the list of possible moves
     * @param p - the point
     * @return point
     */
    Point makeMove(List<Point> possibleMoves, Point p);

    /**
     * Gets the symbol
     * @return the symbol
     */
    char getSymbol();
}
