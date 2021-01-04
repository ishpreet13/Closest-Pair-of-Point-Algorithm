import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * InputReader is a class with static methods that reads and prepare
 * the input data for running closest pair of points algorithm
 * @author Ishpreet Talwar
 */
public class InputReader {

    /**
     * @param fileName: Name of the file that contains the program input data.
     *                  The file should be present in the same folder as the code directory
     * @return An object of class Points which consists list of Point sorted by x and y coordinates
     * @throws FileNotFoundException
     */
    public static Points getPoints(String fileName) throws FileNotFoundException {
        List<String> fileAsListOfString = readFileAsListOfString(fileName);
        // Parsing number of coordinates and save it in n
        int n = Integer.parseInt(fileAsListOfString.get(0));

        // Initializing an object of Points
        Points points = new Points();
        points.setN(n);

        // Parsing next n elements to get x and y coordinates and adding it to object of Points
        for (int i = 1; i <= n; i++) {
            // Splitting the element to get x and y coordinates and trimming extra spaces
            String[] xY = fileAsListOfString.get(i).strip().split("\\s* \\s*");
            // Saving x and y of coordinate as object of type Point
            Point p = new Point(Double.parseDouble(xY[0].strip()), Double.parseDouble(xY[1].strip()));
            // Adding point to points object
            points.addPoint(p);
        }
        // Sorting points by x and y coordinates.
        points.sortByXandY();
        // Assigning indexes to the points by its position in list sorted by x coordinates
        points.assignIndexes();

        return points;
    }

    /**
     * @param fileName: Name of the file that contains the program input data.
     * @return List of strings where 1st element is the number of points and next n
     * elements are the x and y coordinates separated by a space
     * @throws FileNotFoundException
     */
    private static List<String> readFileAsListOfString(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        // Initializing the list to store lines of the input file
        List<String> fileAsListOfString = new ArrayList<String>();
        Scanner scan = new Scanner(file);
        // Reads each line of the file
        while (scan.hasNextLine()) {
            fileAsListOfString.add(scan.nextLine());
        }
        return fileAsListOfString;
    }

}
