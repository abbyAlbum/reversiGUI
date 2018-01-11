package Game;

import GUI.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class GameFlow {

    private char currentPlayer_;
    private int[] turnsLeft_;
    private Board board_;
    private GameLogic logic_;
    private Player p1;
    private Player p2;

    /**
     * constructor for game flow
     * @param boardSize
     */
    public GameFlow(int boardSize, String playerOne, Color colorOne, Color colorTwo) {
        board_ = new Board(boardSize);
        if (playerOne.equals("black"))
            currentPlayer_ = 'X';
        else
            currentPlayer_ = 'O';
        logic_ = new BasicLogic(board_);
        turnsLeft_ = new int[2];
        turnsLeft_[0] = 0;
        turnsLeft_[1] = 0;
        p1 = new HumanPlayer('X', colorOne);
        p2 = new HumanPlayer('O', colorTwo);
    }

    /**
     * runs the game.
     */
    public void run() {
        CellCounter cc = new CellCounter(board_);
        //loadFXML();
        while (true) {
            if (currentPlayer_ == 'X') {
                playOneTurn(p1, p2, cc);
            } else {
                playOneTurn(p2, p1, cc);
            }
            if ((turnsLeft_[0] == 1 && turnsLeft_[1] == 1) || cc.getSpaceCounter() == 0) break;
        }
    }

    /**
     * Loads the FXML
     */
    public void loadFXML() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("game.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reversi");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays a player's turn
     * @param curr player
     * @param opp player
     * @param cc cell counter
     */
    private void playOneTurn(Player curr, Player opp, CellCounter cc) {
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
