import java.io.*;
import java.util.*;

/**
 * The MapAnalyzer class provides static methods to analyze maps of roads,
 * including reading and printing roads, determining the fastest route,
 * and finding routes in a barely connected graph.
 */
public class MapAnalyzer {
    
    /**
     * Prints the list of Road objects to the console.
     *
     * @param roads a list of Road objects to be printed
     */
    private static void printRoads(List<Road> roads) {
        for (Road road : roads) {
            System.out.println(road);
        }
    }

    /**
     * Reads the first line of the input file to get the start and end points for analysis.
     *
     * @param inputFileName the name of the input file
     * @return an array containing the start and end points
     */
    private static String[] getStartEndPoints(String inputFileName) {
        try {
            Scanner sc = new Scanner(new File(inputFileName));
            String[] startEndPoints = sc.nextLine().split("\t");
            sc.close();
            return startEndPoints;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Reads the road data from the input file and returns a list of Road objects.
     *
     * @param inputFileName the name of the input file
     * @return a list of Road objects read from the file
     */
    private static List<Road> readRoads(String inputFileName) {
        List<Road> roads = new ArrayList<Road>();
        try {
            Scanner sc = new Scanner(new File(inputFileName));
            sc.nextLine(); // Skip the first line
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\t");
                String endPoint1 = line[0];
                String endPoint2 = line[1];
                int length = Integer.parseInt(line[2]);
                int roadID = Integer.parseInt(line[3]);
                Road road = new Road(endPoint1, endPoint2, length, roadID, false);
                roads.add(road);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return roads;
    }

    /**
     * Conducts a comprehensive analysis of the roads, including finding the fastest route,
     * the barely connected graph routes, and comparing material usage ratios.
     *
     * @param roads a list of Road objects representing the network of roads
     * @param startPoint the starting point for the route analysis
     * @param endPoint the ending point for the route analysis
     */
    private static void makeAnalyze(List<Road> roads, String startPoint, String endPoint) {
        int totalLength = RoadAnalyzer.getLength(roads);

        // Find the fastest route between the start and end points
        List<Road> fastestRoute = RoadAnalyzer.getFastestRoute(roads, startPoint, endPoint); 
        int fastestRouteLength = RoadAnalyzer.getLength(fastestRoute);
        System.out.println("Fastest Route from " + startPoint + " to " + endPoint + " (" + fastestRouteLength + " KM):");
        printRoads(fastestRoute);

        // Find the barely connected graph routes (similar to a minimum spanning tree)
        List<Road> barelyConnectedGraphRoutes = RoadAnalyzer.getBarelyConnectedGraphRoutes(roads); 
        int barelyConnectedGraphLength = RoadAnalyzer.getLength(barelyConnectedGraphRoutes);
        System.out.println("Roads of Barely Connected Map is:");
        printRoads(barelyConnectedGraphRoutes);

        // Find the fastest route on the barely connected graph
        List<Road> fastestRouteBarelyConnected = RoadAnalyzer.getFastestRoute(barelyConnectedGraphRoutes, startPoint, endPoint);
        int fastestRouteBarelyConnectedLength = RoadAnalyzer.getLength(fastestRouteBarelyConnected);
        System.out.println("Fastest Route from " + startPoint + " to " + endPoint + " on Barely Connected Map (" + fastestRouteBarelyConnectedLength + " KM):");
        printRoads(fastestRouteBarelyConnected);

        // Compare material usage ratios between the original and barely connected maps,
        // and the fastest route lengths between the original and barely connected maps
        System.out.println("Analysis:");
        double metarialUsageRatio = (double) barelyConnectedGraphLength / totalLength;
        System.out.println("Ratio of Construction Material Usage Between Barely Connected and Original Map: " + String.format("%.2f", metarialUsageRatio));
        double fastestRouteRatio = (double) fastestRouteBarelyConnectedLength / fastestRouteLength;
        System.out.print("Ratio of Fastest Route Between Barely Connected and Original Map: " + String.format("%.2f", fastestRouteRatio));
    }

    /**
     * The main method to run the map analysis. It reads input from a specified file and writes output to another file.
     *
     * @param args the command line arguments, where args[0] is the input file name and args[1] is the output file name
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MapAnalyzer <input file> <output file>");
            return;
        }
        try{
            System.setOut(new PrintStream(new FileOutputStream(args[1]))); // Redirect console output to a file with args[1]
        }catch(Exception e){
            System.out.println(e);
        }

        String[] startEndPoints = getStartEndPoints(args[0]); // Read the start and end points from the input file
        String startPoint = startEndPoints[0];
        String endPoint = startEndPoints[1];
        List<Road> roads = readRoads(args[0]); // Read the road data from the input file
        makeAnalyze(roads, startPoint, endPoint); // Analyze the roads
        
        System.out.close();
    }
}