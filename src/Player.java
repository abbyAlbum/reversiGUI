import java.util.List;

public interface Player {

    Point makeMove(List<Point> possibleMoves, Point p);
    char getSymbol();
}
