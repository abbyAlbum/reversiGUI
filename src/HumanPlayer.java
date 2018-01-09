import java.util.List;

public class HumanPlayer implements Player {

    private char symbol_;

/**
 * constructor for HumanPlayer.
 * @param symbol
 */
    public HumanPlayer(char symbol) {
        symbol_ = symbol;
    }

    /**
     * lets the player to make a move
     * @param moves possible moves for the player
     * @return the player's choice
     */
    public Point makeMove(List<Point> moves, Point p) {
        if (moves.isEmpty()) {
            System.out.println("You have no possible moves, other player's turn.");
            return new Point(-1, -1);
        }
        //Point p;
        System.out.println("Please enter row and column (separated with space):");
        while (true) {
            //p = getValidInput();
            Boolean isInMoves = false;
            for (int i = 0; i < moves.size(); ++i) {
                if (p == moves.get(i)) {
                    isInMoves = true;
                    break;
                } else isInMoves = false;
            }
            if (isInMoves) break;
            else {
                System.out.println("Your choice is illegal, please try again:");
            }
        }
        return p;
    }

//    /**
//     * makes sure that we get only numbers
//     * @return the player's choice
//     */
//    public Point getValidInput() {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            int row = sc.nextInt();
//            int col = sc.nextInt();
//            return new Point(row, col);
//            // user didn't input row and column
//            System.out.println("Please enter numbers only." );
//            cin.clear(); // reset failbit
//            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//        }
//    }

    /**
     * gets the player's symbol
     * @return symbol
     */
    public char getSymbol() { return symbol_; }
}
