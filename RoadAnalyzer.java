import java.util.*;

public class RoadAnalyzer {
    public static int getLength(List<Road> route) {
        int totalLength = 0;
        for (Road road : route) {
            totalLength += road.getLength();
        }
        return totalLength;
    }

    public static List<Road> getFastestRoute(List<Road> roads, String startPoint, String endPoint) {
        HashMap<String, HashMap<String, Road>> graph = new HashMap<String, HashMap<String, Road>>();
        for (Road road : roads) {
            String endPoint1 = road.getEndPoint1();
            String endPoint2 = road.getEndPoint2();
            if (!graph.containsKey(endPoint1)) {
                graph.put(endPoint1, new HashMap<String, Road>());
            }
            if (!graph.containsKey(endPoint2)) {
                graph.put(endPoint2, new HashMap<String, Road>());
            }
            graph.get(endPoint1).put(endPoint2, new Road(endPoint1, endPoint2, road.getLength(), road.getRoadID(), false));
            graph.get(endPoint2).put(endPoint1, new Road(endPoint2, endPoint1, road.getLength(), road.getRoadID(), true));
        }

        HashMap<String, Integer> distance = new HashMap<String, Integer>();
        HashMap<String, String> previous = new HashMap<String, String>();
        PriorityQueue<Road> pq = new PriorityQueue<Road>();
        distance.put(startPoint, 0);
        pq.add(new Road(startPoint, startPoint, 0, 0, false));
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            String currentPoint = road.getEndPoint2();
            int length = distance.get(currentPoint);
            if (length > distance.get(currentPoint)) {
                continue;
            }
            for (String nextPoint : graph.get(currentPoint).keySet()) {
                Road nextRoad = graph.get(currentPoint).get(nextPoint);
                int nextLength = length + nextRoad.getLength();
                if (!distance.containsKey(nextPoint) || nextLength < distance.get(nextPoint)) {
                    distance.put(nextPoint, nextLength);
                    previous.put(nextPoint, currentPoint);
                    pq.add(nextRoad);
                }
            }
        }
        if (!distance.containsKey(endPoint)) {
            return null;
        }

        List<Road> route = new ArrayList<Road>();
        String current = endPoint;
        while (!current.equals(startPoint)) {
            String prev = previous.get(current);
            route.add(graph.get(prev).get(current));
            current = prev;
        }
        Collections.reverse(route);
        return route;
    }

    private static String findRoot(HashMap<String, String> parents, String node) {
        while (!parents.get(node).equals(node)) {
            parents.put(node, parents.get(parents.get(node)));
            node = parents.get(node);
        }
        return node;
    }

    public static List<Road> getBarelyConnectedGraphRoutes(List<Road> roads){
        List<Road> barelyConnectedGraphRoutes = new ArrayList<Road>();
        Collections.sort(roads);
        HashMap<String, Integer> ranks = new HashMap<String, Integer>();
        HashMap<String, String> parents = new HashMap<String, String>();

        for (Road road : roads) {
            String endPoint1 = road.getEndPoint1();
            String endPoint2 = road.getEndPoint2();
            if (!ranks.containsKey(endPoint1)) {
                ranks.put(endPoint1, 0);
                parents.put(endPoint1, endPoint1);
            }
            if (!ranks.containsKey(endPoint2)) {
                ranks.put(endPoint2, 0);
                parents.put(endPoint2, endPoint2);
            }
            String root1 = findRoot(parents, endPoint1);
            String root2 = findRoot(parents, endPoint2);
            if (!root1.equals(root2)) {
                barelyConnectedGraphRoutes.add(road);
                if (ranks.get(root1) < ranks.get(root2)) {
                    parents.put(root1, root2);
                } else if (ranks.get(root1) > ranks.get(root2)) {
                    parents.put(root2, root1);
                } else {
                    parents.put(root1, root2);
                    ranks.put(root2, ranks.get(root2) + 1);
                }
            }
        }
        return barelyConnectedGraphRoutes;
    }
}