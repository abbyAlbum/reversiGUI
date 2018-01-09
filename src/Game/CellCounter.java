package Game;

public class CellCounter {

    private Board board_;
    private int xCounter_;
    private int oCounter_;
    private int spaceCounter_;

    /**
     * constructor for Game.CellCounter
     * @param board
     */
    public CellCounter(Board board) {
        board_ = board;
        xCounter_ = 0;
        oCounter_ = 0;
        spaceCounter_ = 0;
    }

    /**
     * counts the num of cells of each type in each turn
     */
    public void count() {
        Point p;
        xCounter_ = 0;
        oCounter_ = 0;
        spaceCounter_ = 0;
        for (int i = 0; i < board_.getSize(); ++i) {
            for (int j = 0; j < board_.getSize(); ++j) {
                p = new Point(i, j);
                if (board_.getStatus(p) == 'X') xCounter_++;
                if (board_.getStatus(p) == 'O') oCounter_++;
                if (board_.getStatus(p) == ' ') spaceCounter_++;
            }
        }
    }

    /**
     * gets the num of 2nd player's occupied cells
     * @return num of cells
     */
    public int getOCounter(){ return oCounter_; }

    /**
     * gets the num of unoccupied
     * @return num of cells
     */
    public int getSpaceCounter(){ return spaceCounter_; }

    /**
     * gets the num of 1st player's occupied cells
     * @return num of cells
     */
    public int getXCounter(){ return xCounter_; }
}
