public class Cell{

    private char val_;
    private Point place_;

    /**
     * constructor for Cell
     */
    public Cell() {}

    /**
    * constructor for Cell
    * @param p point
    * @param val value
    */
    public Cell(Point p, char val) {
        place_ = p;
        val_ = val;
    }

    /**
     * gets the cell's value
     * @return value
     */
    public char getValue(){ return val_; }

    /**
     * sets the cell's value
     * @param val value
     */
    public void setValue(char val) { val_ = val; }

    /**
     * sets the place of the cell
     * @param p point
     */
    public void setPlace(Point p) {
        place_ = new Point(p.getX(), p.getY());
    }

}
