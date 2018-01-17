package gui;

import Game.*;
import Game.Point;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoardController implements Initializable{

    @FXML
    private HBox root;
    @FXML
    private Button backBtn;

    private Integer currentPlayer_;
    private Integer k;
    private int[] turnsLeft_;
    /**
     * Creates the board controller
     */
    public BoardController() {
        turnsLeft_ = new int[2];
        turnsLeft_[0] = 0;
        turnsLeft_[1] = 0;
    }

    /**
     * Initiliazes the game
     * @param location - the location
     * @param resources - the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SettingsReader sr = new SettingsReader("Settings.txt");
        sr.readFile();
        Integer counter = 0;
        GuiBoard gb = new GuiBoard(new Board(sr.getSize()), sr.getColourOne(), sr.getColourTwo());
        VBox vb = new VBox(20);
        backBtn = new Button();
        backBtn.setText("Back");
        backBtn.setOnAction(event -> {goBack();});
        Text current = new Text();
        Text player = new Text();
        Text score1 = new Text();
        Text score2 = new Text();
        setVb(vb, current, player, score1, score2);
        gb.setPrefWidth(600);
        gb.setPrefHeight(400);
        root.getChildren().add(0, gb);
        root.getChildren().add(1, vb);
        currentPlayer_ = 1;
        k = 0;
        CellCounter cc = new CellCounter(gb.getBoard());
        Player p1 = new HumanPlayer('X', sr.getColourOne());
        Player p2 = new HumanPlayer('O', sr.getColourTwo());
        gb.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
            Point move = convert(e.getX(), e.getY(), gb.getHeight() / gb.getBoard().getSize());
            if (currentPlayer_ == 1)
                run(move, p1, p2, gb, counter, cc, player, score1, score2);
            else
                run(move, p2, p1, gb, counter, cc, player, score1, score2);
            if ((turnsLeft_[0] == 1 && turnsLeft_[1] == 1) || cc.getSpaceCounter() == 0) {
                if (k == 0) endGame(cc);
                k++;
            }
        });
        gb.draw(p1, p2, counter, cc, currentPlayer_);
        changeScene(gb, p1, p2 ,counter, cc);

    }

    /**
     * Sets the vbox
     * @param vb - the vbox
     * @param current - the current player text
     * @param player - the player text
     * @param score1 - the first score text
     * @param score2 - the second score text
     */
    private void setVb(VBox vb, Text current, Text player, Text score1, Text score2) {
        current.setText("Current Player: ");
        current.setFont(new Font(23));
        player.setText("   Player One");
        player.setFont(new Font(23));
        score1.setText("Player 1: 2");
        score1.setFont(new Font(20));
        score2.setText("Player 2: 2");
        score2.setFont(new Font(20));
        vb.getChildren().add(current);
        vb.getChildren().add(player);
        vb.getChildren().add(score1);
        vb.getChildren().add(score2);
        vb.getChildren().add(backBtn);
    }

    /**
     * Changes the size of the screen
     * @param gb - the guiBoard
     * @param p1 - the first player
     * @param p2 - the second player
     * @param counter - the counter
     */
    private void changeScene(GuiBoard gb, Player p1, Player p2, Integer counter, CellCounter cc){
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            gb.setPrefWidth(boardNewWidth);
            gb.draw(p1, p2, counter, cc, currentPlayer_);
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            gb.setPrefHeight(newValue.doubleValue());
            gb.draw(p1 , p2, counter, cc, currentPlayer_);
        });

    }
    /**
     * Runs the game
     * @param move - the players move
     * @param p1 - the fist player
     * @param p2 - the second player
     * @param gb - the guiBoard
     */
    public void run(Point move, Player p1, Player p2, GuiBoard gb, Integer counter, CellCounter cc,
                    Text player, Text score1, Text score2) {
        int x = move.getX() - 1;
        int y = move.getY() - 1;
        boolean flag = true;
        Point point = new Point(x, y);
        BasicLogic bl = new BasicLogic(gb.getBoard());
        List<Point> points = bl.getPossibleMoves(p1, p2);
        Point choice = p1.makeMove(points, point);
        //if the point is contained in the list of possible moves
        if(choice.getX() != -1 && choice.getX() != -2) {
            gb.getBoard().putChoice(choice, p1, p2);
            cc.count();
            flag = checkMoves(player, p1, p2, cc, gb.getBoard());
            counter++;
            gb.draw(p1, p2, counter, cc, currentPlayer_);
            score1.setText("Player 1: " + cc.getXCounter());
            score2.setText("Player 2: " + cc.getOCounter());
            if (flag) {
                if (turnsLeft_[0] == 1 && currentPlayer_ == 1) turnsLeft_[0] = 0;
                if (turnsLeft_[1] == 1 && currentPlayer_ == 2) turnsLeft_[1] = 0;
            }
            switchTurn(player);
        }
    }

    /**
     * Checks the players moves
     * @param player - the player text
     * @param p1 - first player
     * @param p2 - second player
     * @param cc - cell counter
     * @param b - the board
     * @return true or false
     */
    private boolean checkMoves(Text player, Player p1, Player p2, CellCounter cc, Board b) {
        GameLogic gl = new BasicLogic(b);
        List<Point> moves = gl.getPossibleMoves(p2, p1);
        List<Point> moves1 = gl.getPossibleMoves(p1, p2);
        boolean flag = true;
        if(moves.isEmpty() && (!(turnsLeft_[0] == 1 && turnsLeft_[1] == 1) || cc.getSpaceCounter() == 0)) {
            switchTurn(player);
            turnsLeft_[currentPlayer_ - 1] = 0;
            flag = false;
            if (moves1.isEmpty())
                turnsLeft_[3 - currentPlayer_ - 1] = 1;
        }
        return flag;
    }

    /**
     * Button to return to menu
     */
    private void goBack() {
        try {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader((ClassLoader.getSystemClassLoader().getResource("menu.fxml")));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Reversi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ends the game
     * @param cc - the cellcounter
     */
    private void endGame(CellCounter cc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);
        if (cc.getXCounter() > cc.getOCounter())
            alert.setContentText("Congratulations Player One! You Win!!");
        else if (cc.getOCounter() > cc.getXCounter())
            alert.setContentText("Congratulation Player Two! You Win!!");
        else
            alert.setContentText("It's a Tie!");

        alert.showAndWait();
    }

    /**
     * Changes the players turn
     * @param player - the player text
     */
    private void switchTurn(Text player) {
        currentPlayer_ = 3 - currentPlayer_;
        if (currentPlayer_ == 2)
            player.setText("   Player two");
        else
            player.setText("   Player one");
    }

    /**
     * Converts from the functions point to out point
     * @param x - x co-ordinate
     * @param y - y co-ordinate
     * @return our point
     */
    private Point convert(double x, double y, double cellSize) {
        int newY = 1 + (int) (x / cellSize);
        int newX = 1 + (int) (y / cellSize);
        return new Point(newX, newY);
    }
}
