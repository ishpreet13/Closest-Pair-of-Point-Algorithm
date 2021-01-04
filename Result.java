/**
 * Result class store result of closest pair of points algorithm and its recursion.
 * Result is represented by its startIndex, finalIndex and the minimumDistance
 * Functionality includes calculation minimum of 2 results and formatting of result to console
 * by overriding toString
 * @author Ishpreet Talwar
 */
public class Result {
    /**
     * Start index of the problem for which the result is calculated
     */
    private int startIndex;
    /**
     * End index of the problem for which the result is calculated
     */
    private int endIndex;

    /**
     * Getter for startIndex
     * @return startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Getter for endIndex
     * @return endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }


    /**
     * Closest distance between pair of points
     */
    private double minimumDistance;

    /**
     * Getter for minimumDistance
     *
     * @return minimumDistance
     */
    public double getMinimumDistance() {
        return minimumDistance;
    }

    /**
     * Constructor to initialize Result
     *
     * @param point1Index
     * @param endIndex
     * @param minimumDistance
     * pre: None
     * post: Result object is initialized
     */
    public Result(int point1Index, int endIndex, double minimumDistance) {
        this.startIndex = point1Index;
        this.endIndex = endIndex;
        this.minimumDistance = minimumDistance;
    }

    /**
     * Gets minimum of 2 results and calculate the indexes for the result.
     * The left and right are sorted by x coordinates, hence start of leftResult
     * should be the start index and end of rightResult should be the end index of the minimum result
     * @param leftResult
     * @param rightResult
     * @return Result with minimum between input results based on their minimumDistance,
     *         start index of left result and end index of right result
     */
    public static Result minResult(Result leftResult, Result rightResult) {
        double minDistance = 0;
        if (leftResult.minimumDistance < rightResult.minimumDistance) {
            minDistance = leftResult.minimumDistance;
        } else {
            minDistance = rightResult.minimumDistance;
        }
        return new Result(leftResult.startIndex, rightResult.endIndex, minDistance);
    }

    /**
     * Overrides toString method to display the result in the format -
     * "D[startIndex,endIndex]: minimumDistance"
     * @return String formatted as "D[startIndex,endIndex]: minimumDistance"
     */
    @Override
    public String toString() {
        return String.format("D[%d,%d]: %.4f", startIndex, endIndex, minimumDistance);
    }
}
