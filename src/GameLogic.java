import java.util.List;

public interface GameLogic {

    List<Point> getPossibleMoves(Player curr, Player opp);
}
