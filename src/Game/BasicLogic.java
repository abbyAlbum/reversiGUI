package Game;

import java.util.*;

public class BasicLogic implements GameLogic {
    private Board board_;

    /**
     * constructor for Game.BasicLogic
     * @param board
     */
    public BasicLogic(Board board) {
        board_ = board;
    }

    /**
     * returns the player's possible moves
     * @param curr current player
     * @param opp opponent
     * @return possible moves
     */
    public List<Point> getPossibleMoves(Player curr, Player opp) {
        List<Point> possibleMoves = new LinkedList<>();
        for (int i = 0; i < board_.getSize(); ++i) {
            for (int j = 0; j < board_.getSize(); ++j) {
                if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) {
                    List<Point> moves = checkAround(i, j, curr, opp);
                    possibleMoves.addAll(moves);
                }
            }
        }
        //List<Game.Point> moves = possibleMoves;
        Collections.sort(possibleMoves, new ComparePoint());
        Set<Point> pointSet = new HashSet<>(possibleMoves);
        List<Point> moves = new LinkedList<>(pointSet);
        //possibleMoves.erase(unique(possibleMoves.begin(), possibleMoves.end()), possibleMoves.end());
        //printOptions(moves);
        return possibleMoves;
    }

    /**
     * returns the possible moves from a specific point.
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return point's possible moves
     */
    private List<Point> checkAround(int i, int j, Player curr, Player opp) {
        List<Point> moves = new LinkedList<>(), sides, diag;
        sides = checkSides(i, j, curr, opp);
        moves.addAll(sides);
        diag = checkDiag(i, j, curr, opp);
        moves.addAll(diag);
        return moves;
    }

    /**
     * adds point to vector if valid
     * @param moves vector
     * @param p potential move to add
     */
    private void addIfValid(List<Point> moves, Point p) {
        if (p.getX() != -1) moves.add(p);
    }

    /**
     * checks up
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkUp(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        if (i != 0)
            return checkUp(i - 1, j, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks down
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkDown(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        if (i != board_.getSize() - 1)
            return checkDown(i + 1, j, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks left
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkLeft(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        if (j != 0)
            return checkLeft(i, j - 1, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks rigt
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkRight(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        if (j != board_.getSize() - 1)
            return checkRight(i, j + 1, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks up left
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkUpL(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        if (j != 0 && i != 0)
            return checkUpL(i - 1, j - 1, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks up right
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkUpR(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        else if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        else if (j != board_.getSize() - 1 && i != 0)
            return checkUpR(i - 1, j + 1, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks down right
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkDownR(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        else if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        else if (j != board_.getSize() - 1 && i != board_.getSize() - 1)
            return checkDownR(i + 1, j + 1, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks down left
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return potential move
     */
    private Point checkDownL(int i, int j, Player curr, Player opp) {
        if (board_.getStatus(new Point(i, j)) == curr.getSymbol()) return new Point(-1, -1);
        else if (board_.getStatus(new Point(i, j)) == ' ') return new Point(i + 1, j + 1);
        else if (j != 0 && i != board_.getSize() - 1)
            return checkDownL(i + 1, j - 1, curr, opp);
        else return new Point(-1, -1);
    }

    /**
     * checks the sides
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return vector of possible moves
     */
    List<Point> checkSides(int i, int j, Player curr, Player opp) {
        List<Point> moves = new LinkedList<>();
        Point p;
        if (i != 0 && board_.getStatus(new Point(i - 1, j)) == opp.getSymbol()) {
            p = checkUp(i - 1, j, curr, opp);
            addIfValid(moves, p);
        }
        if (i != board_.getSize() - 1 && board_.getStatus(new Point(i + 1, j)) == opp.getSymbol()) {
            p = checkDown(i + 1, j, curr, opp);
            addIfValid(moves, p);
        }
        if (j != 0 && board_.getStatus(new Point(i, j - 1)) == opp.getSymbol()) {
            p = checkLeft(i, j - 1, curr, opp);
            addIfValid(moves, p);
        }
        if (j != board_.getSize() - 1 && board_.getStatus(new Point(i, j + 1)) == opp.getSymbol()) {
            p = checkRight(i, j + 1, curr, opp);
            addIfValid(moves, p);
        }
        return moves;
    }

    /**
     * checks the diagonals
     * @param i row
     * @param j col
     * @param curr player
     * @param opp player
     * @return vector of possible moves
     */
    List<Point> checkDiag(int i, int j, Player curr, Player opp) {
        List<Point> moves = new LinkedList<>();
        Point p;
        if (i != 0 && j != 0 && board_.getStatus(new Point(i - 1, j - 1)) == opp.getSymbol()) {
            p = checkUpL(i - 1, j - 1, curr, opp);
            addIfValid(moves, p);
        }
        if (i != 0 && j != board_.getSize() - 1 && board_.getStatus(new Point(i - 1, j + 1)) == opp.getSymbol()) {
            p = checkUpR(i - 1, j + 1, curr, opp);
            addIfValid(moves, p);
        }
        if (i != board_.getSize() - 1 && j != board_.getSize() - 1
                && board_.getStatus(new Point(i + 1, j + 1)) == opp.getSymbol()) {
            p = checkDownR(i + 1, j + 1, curr, opp);
            addIfValid(moves, p);
        }
        if (i != board_.getSize() - 1 && j != 0 && board_.getStatus(new Point(i + 1, j - 1)) == opp.getSymbol()) {
            p = checkDownL(i + 1, j - 1, curr, opp);
            addIfValid(moves, p);
        }
        return moves;
    }

    /**
     * prints the possible moves
     * @param moves vector
     */
    public void printOptions(List<Point> moves) {
        for (int i = 0; i < moves.size(); ++i) {
            if (i == moves.size() - 1) moves.get(i).print();
            else {
                moves.get(i).print();
                System.out.print(",");
            }
        }
        System.out.println();
    }
}
