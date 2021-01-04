import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Points class store the data of all the points of Class type Point and performs
 * operation required for closest pair of points algorithm.
 * Points Object is represented by a list of points provided in program input file,
 * list of points sorted by X coordinates, list of points sorted by Y coordinates
 * Functionality includes adding new points, getting distance between 2 points at index
 * @author Ishpreet Talwar
 */
public class Points {
    /**
     * Number of points
     */
    private int n;

    /**
     * List of points
     */
    private List<Point> points = new ArrayList<Point>();

    /**
     * List of points sorted by its x coordinate in ascending order
     */
    private List<Point> pointsSortedByX = new ArrayList<Point>();

    /**
     * List of points sorted by its y coordinate in ascending order
     */
    private List<Point> pointsSortedByY = new ArrayList<Point>();


    /**
     * Getter for n
     * @return n: number of points
     */
    public int getN() {
        return n;
    }

    /**
     * Setter for n
     * @param n: number of points
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Getter for pointsSortedByX
     * @return pointsSortedByX
     */
    public List<Point> getPointsSortedByX() {
        return pointsSortedByX;
    }

    /**
     * Add point to list of point
     * @param point: point Object to be added to list of Points
     */
    public void addPoint(Point point){
        this.points.add(point);
    }

    /**
     * Gets euclidean distance between 2 points from pointsSortedByX
     * @param i : Index of a point in pointsSortedByX
     * @param j : Index of a point in pointsSortedByX
     * @return Euclidean distance between 2 points in double format
     */
    public double getDistanceBetweenPointsAtIndexes(int i, int j){
        Point p1 = pointsSortedByX.get(i);
        Point p2 = pointsSortedByX.get(j);
        return p1.getDistanceFrom(p2);
    }

    /**
     * Sorts the input list of points by its x and y cordinate
     * and saves it i
     * pre: pointsSortedByX and pointsSortedByY were assigned as null
     * post: pointsSortedByX and pointsSortedByY are assigned to list of points
     * sorted by x coordinate and y coordinate values respectively
     */
    public void sortByXandY(){
        this.pointsSortedByX = points.stream()
                .sorted(Comparator.comparing(Point::getX))
                .collect(Collectors.toList());
        this.pointsSortedByY = points.stream()
                .sorted(Comparator.comparing(Point::getY))
                .collect(Collectors.toList());
    }

    /**
     * Assigns indexes to the points by its position in list sorted by x coordinates
     * pre: The index field of points was not set
     * post: The index property of all the points are assigned based on its index in the pointsSortedByX
     */
    public void assignIndexes(){
        int index = 0;
        for (Point p: this.pointsSortedByX){
            p.setIndex(index);
            index++;
        }
    }

    /**
     * Brute force approach to find closest pair of points.
     * This can calculate for any number of points, however the algorithm just calls
     * this method whenever the number of points are equal or less than 3
     * @return Object of type Result containing the startIndex, endIndex and the minimum distance
     */
    public Result getMinimumDistanceUsingBruteForceApproach(){
        // Getting the number of points
        int n = this.n;
        // Initializing minimum distance to max double value
        double minDistance = Double.MAX_VALUE;
        // Initializing start index - Minimum index of a coordinate from list of points
        int startIndex = this.getPointsSortedByX().get(0).getIndex();
        // Initializing end index - Maximum index of a coordinate from list of points
        int endIndex = this.getPointsSortedByX().get(n-1).getIndex();

        // Iterating over all possible points to mind the minimum distance
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++){
                double dist = this.getDistanceBetweenPointsAtIndexes(i, j);
                if (dist < minDistance) {
                    minDistance = dist;
                }
            }
        }
        // Assigning the result of the problem to Result object
        Result result = new Result(startIndex, endIndex, minDistance);
        // Printing the result of brute force algorithm on the console
        System.out.println(result);
        return result;
    }

    /**
     *
     * @return Middle index of the list of points
     */
    private int getMiddleIndex(){
        int middle;
        if (this.n % 2 == 0) {
            middle = (int) this.n / 2;
        }
        else {
            middle = (int) ((this.n + 1) / 2);
        }
        return middle;
    }

    /**
     * Divides the points object into left and right point objects
     * @return An array of Points object with left side as the first element and right as the second
     */
    public Points[] dividePointsInLeftAndRight(){
        // Finding the index of middle using the number of points
        int middle = getMiddleIndex();
        // Getting the mid point from pointsSortedByX (list of points sorted by X)
        Point midPointX = pointsSortedByX.get(middle);
        //Initializing left anf right Points object
        Points leftPoints = new Points();
        Points rightPoints = new Points();

        // Initializing list of points sorted by x and y coordinates for left and right Points objects
        List<Point> leftPointsSortedByX = new ArrayList();
        List<Point> leftPointsSortedByY = new ArrayList();
        List<Point> rightPointsSortedByX = new ArrayList();
        List<Point> rightPointsSortedByY = new ArrayList();

        // Assigning variables of left and right points objects
        int countMid = 0;
        for (int i = 0; i < n; i++)
        {
            // Getting 1st half of pointsSortedByX to left points and the 2nd half to right points object resp.
            if (i < middle){
                leftPointsSortedByX.add(this.pointsSortedByX.get(i));
            } else {
                rightPointsSortedByX.add(this.pointsSortedByX.get(i));
            }

            // Getting the corresponding points from pointsSortedByY in order for left and right points
            if (this.pointsSortedByY.get(i).getX() < midPointX.getX() && countMid < middle) {
                leftPointsSortedByY.add(this.pointsSortedByY.get(i));
                countMid++;
            }
            else {
                rightPointsSortedByY.add(this.pointsSortedByY.get(i));
            }
        }

        // Setting pointsSortedByX and pointsSortedByY lists for left points object
        leftPoints.setN(middle);
        leftPoints.pointsSortedByX = leftPointsSortedByX;
        leftPoints.pointsSortedByY = leftPointsSortedByY;

        // Setting pointsSortedByX and pointsSortedByY lists for right points object
        rightPoints.setN(this.n - middle);
        rightPoints.pointsSortedByX = rightPointsSortedByX;
        rightPoints.pointsSortedByY = rightPointsSortedByY;

        // Returning left and right points as an array
        return(new Points[]{leftPoints, rightPoints});
    }

    /**
     * Finds the strip points and calculates the minimum distance in strip points lesser than minimum
     * distance from left and right region passed as an input
     * @param minFromLeftAndRight: Result containing minimum distance from left and right side
     * @return Result containing the minimum distance using left, right and strip points
     */
    public Result getMinDistanceIncludingStripPoints(Result minFromLeftAndRight) {
        // Initializing the minimum distance
        double minDistance = minFromLeftAndRight.getMinimumDistance();
        // Initializing the strip points
        List<Point> stripPoints = new ArrayList<Point>();
        // Getting the midpoint from pointsSortedByX
        int middle = getMiddleIndex();
        Point midPointX = pointsSortedByX.get(middle);

        // Finding the strip points from midPointX that are within the minimumDistance from minFromLeftAndRight
        for (Point p : this.pointsSortedByY) {
            if (Math.abs(p.getX() - midPointX.getX()) < minFromLeftAndRight.getMinimumDistance()) {
                stripPoints.add(p);
            }
        }
        // Iterating over strip points to calculate the minimum distance between closest pair inside strip
        // and the minimum distance passed in input.
        int sizeOfStripPoints = stripPoints.size();
        for (int i = 0; i < sizeOfStripPoints; i++)
            for (int j = i + 1; j < sizeOfStripPoints; j++) {
                if (stripPoints.get(j).getY() - stripPoints.get(i).getY() < minDistance) {
                    double distanceBetweenIandJ = stripPoints.get(i).getDistanceFrom(stripPoints.get(j));
                    if (distanceBetweenIandJ < minDistance) {
                        minDistance = distanceBetweenIandJ;
                    }
                }
            }
        return new Result(minFromLeftAndRight.getStartIndex(), minFromLeftAndRight.getEndIndex(), minDistance);
    }

}
