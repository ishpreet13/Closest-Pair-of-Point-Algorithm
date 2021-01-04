import java.io.FileNotFoundException;

/**
 * The main class of Program2 to run closest pair of point algorithm
 * @author Ishpreet Talwar
 */
public class Program2 {
    /**
     * The main method reads Points from program2data.txt and saves it as object of class Points
     * Using the Points object, closest pair algorithm is executed and the result is printed in console.
     */
    public static void main(String[] args) throws FileNotFoundException {
       Points points = InputReader.getPoints("program2data.txt");
       ClosestPairAlgorithm.runAlgorithm(points);
    }
}
