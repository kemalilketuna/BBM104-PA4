import java.io.*;
import java.util.*;

public class MapAnalyzer {
    private static void printRoads(List<Road> roads) {
        for (Road road : roads) {
            System.out.println(road);
        }
    }

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

    private static void makeAnalyze(List<Road> roads, String startPoint, String endPoint) {
        int totalLength = RoadAnalyzer.getLength(roads);

        List<Road> fastestRoute = RoadAnalyzer.getFastestRoute(roads, startPoint, endPoint);
        int fastestRouteLength = RoadAnalyzer.getLength(fastestRoute);
        System.out.println("Fastest Route from " + startPoint + " to " + endPoint + " (" + fastestRouteLength + " KM):");
        printRoads(fastestRoute);

        List<Road> barelyConnectedGraphRoutes = RoadAnalyzer.getBarelyConnectedGraphRoutes(roads);
        int barelyConnectedGraphLength = RoadAnalyzer.getLength(barelyConnectedGraphRoutes);
        System.out.println("Roads of Barely Connected Map is:");
        printRoads(barelyConnectedGraphRoutes);

        List<Road> fastestRouteBarelyConnected = RoadAnalyzer.getFastestRoute(barelyConnectedGraphRoutes, startPoint, endPoint);
        int fastestRouteBarelyConnectedLength = RoadAnalyzer.getLength(fastestRouteBarelyConnected);
        System.out.println("Fastest Route from " + startPoint + " to " + endPoint + " on Barely Connected Map (" + fastestRouteBarelyConnectedLength + " KM):");
        printRoads(fastestRouteBarelyConnected);

        System.out.println("Analysis:");
        double metarialUsageRatio = (double) barelyConnectedGraphLength / totalLength;
        System.out.println("Ratio of Construction Material Usage Between Barely Connected and Original Map: " + String.format("%.2f", metarialUsageRatio));
        double fastestRouteRatio = (double) fastestRouteBarelyConnectedLength / fastestRouteLength;
        System.out.print("Ratio of Fastest Route Between Barely Connected and Original Map: " + String.format("%.2f", fastestRouteRatio));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MapAnalyzer <input file> <output file>");
            return;
        }
        try{
            System.setOut(new PrintStream(new FileOutputStream(args[1])));
        }catch(Exception e){
            System.out.println(e);
        }

        String[] startEndPoints = getStartEndPoints(args[0]);
        String startPoint = startEndPoints[0];
        String endPoint = startEndPoints[1];
        List<Road> roads = readRoads(args[0]);
        makeAnalyze(roads, startPoint, endPoint);
        
        System.out.close();
    }
}