package Game;

import java.util.List;

public interface GameLogic {

    /**
     * Gets the list of possible moves
     * @param curr - current player
     * @param opp - other player
     * @return list of moves
     */
    List<Point> getPossibleMoves(Player curr, Player opp);
}
