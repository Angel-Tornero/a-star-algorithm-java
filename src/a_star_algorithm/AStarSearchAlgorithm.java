package a_star_algorithm;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class AStarSearchAlgorithm {
    private static final byte DISTANCE_BETWEEN_NODES = 1;

    // Define a custom comparator for PriorityQueue based on fScore
    private static class NodeComparator implements Comparator<GridCell> {
        private Map<GridCell, Double> fScore;

        public NodeComparator(Map<GridCell, Double> fScore) {
            this.fScore = fScore;
        }

        @Override
        public int compare(GridCell a, GridCell b) {
            return Double.compare(fScore.getOrDefault(a, Double.POSITIVE_INFINITY), fScore.getOrDefault(b, Double.POSITIVE_INFINITY));
        }
    }

    public static List<GridCell> solve(ShortestPathProblem problem) {
        Map<GridCell, Double> gScore = new HashMap<>(), fScore = new HashMap<>();
        Map<GridCell, GridCell> cameFrom = new HashMap<>();

        // Use a PriorityQueue instead of a List
        PriorityQueue<GridCell> openSet = new PriorityQueue<>(new NodeComparator(fScore));
        openSet.add(problem.startPoint);

        gScore.put(problem.startPoint, 0.0);
        fScore.put(problem.startPoint, heuristicFunction(problem.startPoint, problem.finalPoint));

        while (!openSet.isEmpty()) {
            GridCell current = openSet.poll();  // Get the node with the lowest fScore

            if (current == problem.finalPoint) {
                return reconstructPath(cameFrom, current);
            }

            for (GridCell neighbour: getNeighbours(current, problem.map)) {
                double tentativeGScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + DISTANCE_BETWEEN_NODES;

                if (tentativeGScore < gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeGScore);
                    fScore.put(neighbour, tentativeGScore + heuristicFunction(neighbour, problem.finalPoint));

                    // Only add the neighbour if it is not already in the openSet
                    if (!openSet.contains(neighbour)) {
                        openSet.add(neighbour);
                    }
                }
            }
        }

        return null;
    }

    private static List<GridCell> reconstructPath(Map<GridCell, GridCell> cameFrom, GridCell current) {
        List<GridCell> solution = new ArrayList<>();
        solution.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            solution.add(current);
        }
        return solution;
    }

    private static List<GridCell> getNeighbours(GridCell node, GridMap map) {
        List<GridCell> neighbours = new ArrayList<>();
        // Neighbor directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int newX = node.posX + dx[i];
            int newY = node.posY + dy[i];
            if (newX >= 0 && newY >= 0 && newX < map.width && newY < map.height) {
                // If it's not an obstacle, add it to the neighbors list
                if (!map.positions[newX][newY].isObstacle) {
                    neighbours.add(map.positions[newX][newY]);
                }
            }
        }
        return neighbours;
    }

    private static double heuristicFunction(GridCell node, GridCell finalPoint) {
        return Math.hypot(node.posX - finalPoint.posX, node.posY - finalPoint.posY);
    }
}
