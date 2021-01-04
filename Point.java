import static java.lang.Math.*;

/**
 * Point class saves data for a single point
 * Point is represented by its x coordinate, y coordinate and its index in sorted list of points by
 * x coordinates in ascending order
 * Functionality includes getting the distance of current point from another point
 * @author Ishpreet Talwar
 */
public class Point {
    /**
     * x coordinate of a point
     */
    private double x;

    /**
     * Y coordinate of a point
     */
    private double y;

    /**
     * Index of a point in sorted list by x coordinate
     */
    private int index;

    /**
     * @param x: x coordinate
     * @param y: y coordinate
     * pre: None
     * post: Point is initialized with its x and y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x coordinate
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for y coordinate
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * @param index index of coordinate in sorted list by its x coordinate
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return index of coordinate in sorted list by its x coordinate
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param other Point object of other coordinate
     * @return Euclidean distance of this coordinate from other in double datatype
     */
    public double getDistanceFrom(Point other) {
        return sqrt(pow((this.y - other.y), 2) + pow((this.x - other.x), 2));
    }

}
