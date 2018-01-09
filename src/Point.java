public class Point {
    //
// Created by eyal moskowitz 314074303 on 02/11/17.
//

    private int x_;
    private int y_;

    /**
     * empty constructor for Point.
     */
    public Point() {}

    /**
     * constructor for Point.
     * @param x value
     * @param y value
     */
    Point(int x, int y) {
        x_ = x;
        y_ = y;
    }

    /**
     * gets the x value of the point.
     * @return x value
     */
    public int getX() { return x_; }

    /**
     * gets the y value of the point.
     * @return y value
     */
    public int getY() { return y_; }

    /**
     * sets the x and y values of the point.
     * @param x value
     * @param y value
     */
    public void setPoint(int x, int y) {
        x_ = x;
        y_ = y;
    }

    /**
     * prints the point.
     */
    public void print() {
        int x = this.getX();
        int y = this.getY();
        System.out.println("(" + x +"," + y +")" );
    }
}
