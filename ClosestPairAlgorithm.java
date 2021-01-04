/**
 * ClosestPairAlgorithm is the class that drives the closest pair of point algorithm by
 * divide and conquer approach
 * Functionality includes running Closest pair of points algorithm which recursively finds
 * the shortest distance between 2 points
 * @author Ishpreet Talwar
 */
public class ClosestPairAlgorithm {
    /**
     * The overall driver of closest pair of points algorithm
     * pre: Points object with list of points ordered by X and Y coordinates
     * post: Minimum distance between pair of points
     *
     * @param points: Points object including sorted list by X and Y coordinates
     * @return Result object specifying the minimum distance with start and end index
     */
    public static Result runAlgorithm(Points points) {
        // Using brute force algorithm for less than or equal to 3 points
        if (points.getN() <= 3) {
            return points.getMinimumDistanceUsingBruteForceApproach();
        }
        // Dividing the set of points into left and right based on midpoint sorted by X coordinate
        Points[] leftRightPoints = points.dividePointsInLeftAndRight();

        // Running closest pair of points algorithm on left set of points
        Result minLeft = runAlgorithm(leftRightPoints[0]);
        //  Running closest pair of points algorithm on left set of points
        Result minRight = runAlgorithm(leftRightPoints[1]);

        // Finding smaller of 2 distances from left and right recursion
        Result minFromLeftAndRight = Result.minResult(minLeft, minRight);

        // Finding minimum distance using the points in the strip that are closer than the distance in minDistanceResult
        Result minDistanceResult = points.getMinDistanceIncludingStripPoints(minFromLeftAndRight);

        // Printing the result of the recursion in console
        System.out.println(minDistanceResult);

        return minDistanceResult;
    }
}