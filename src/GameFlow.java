import java.util.LinkedList;
import java.util.List;

public class GameFlow {

    private char currentPlayer_;
    private int[] turnsLeft_;
    private Board board_;
    private GameLogic logic_;

    /**
     * constructor for game flow
     * @param boardSize
     */
    public GameFlow(int boardSize) {
        board_ = new Board(boardSize);
        currentPlayer_ = 'X';
        logic_ = new BasicLogic(board_);
        turnsLeft_ = new int[2];
        turnsLeft_[0] = 0;
        turnsLeft_[1] = 0;
    }

    /**
     * runs the game.
     */
    public void run() {
        HumanPlayer player1 = new HumanPlayer('X');
        HumanPlayer player2 = new HumanPlayer('O');
        HumanPlayer p1 = player1, p2 = player2;
        CellCounter cc = new CellCounter(board_);
        while (true) {
            System.out.println(currentPlayer_ + ": It's your turn.");
            if (currentPlayer_ == 'X') {
                playOneTurn(p1, p2, cc);
            } else {
                playOneTurn(p2, p1, cc);
            }
            if ((turnsLeft_[0] == 1 && turnsLeft_[1] == 1) || cc.getSpaceCounter() == 0) break;
        }
        if (cc.getXCounter() > cc.getOCounter())
            System.out.println("Player 1 wins with " + cc.getXCounter() + " tiles");
        else
            System.out.println("Player 2 wins with " + cc.getOCounter() + " tiles" );
    }

    /**
     * plays a player's turn
     * @param curr player
     * @param opp player
     * @param cc cell counter
     */
    public void playOneTurn(Player curr, Player opp, CellCounter cc) {
        List<Point> moves;
        Point choice;
        Point p = new Point(1, 1);
        moves = logic_.getPossibleMoves(curr, opp);
        choice = curr.makeMove(moves, p);
        if (choice.getX() == -1 && choice.getY() == -1) {
            if (currentPlayer_ == 'X') turnsLeft_[0] = 1;
            else turnsLeft_[1] = 1;
            currentPlayer_ = opp.getSymbol();
            return;
        }
        if (turnsLeft_[0] == 1 && currentPlayer_ == 'X') turnsLeft_[0] = 0;
        if (turnsLeft_[1] == 1 && currentPlayer_ == 'O') turnsLeft_[1] = 0;
        choice.setPoint(choice.getX() - 1, choice.getY() - 1);
        Point ch = choice;
        board_.putChoice(ch, curr, opp);
        cc.count();
        currentPlayer_ = opp.getSymbol();
    }

}
