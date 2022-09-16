package search_algorithm;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class AStarSearchAlgorithm {
    private static final byte DISTANCE_BETWEEN_NODES = 1;
    
    public static List<GridCell> solve(ShortestPathProblem problem) {
        Map<GridCell, Double> gScore = new HashMap<>(), fScore = new HashMap<>();
        Map<GridCell, GridCell> cameFrom = new HashMap<>();
        List<GridCell> openSet = new ArrayList<>();
        openSet.add(problem.startPoint);
        gScore.put(problem.startPoint, 0.0);
        fScore.put(problem.startPoint, heuristicFunction(problem.startPoint, problem.finalPoint));
        while (!openSet.isEmpty()) {
            putLowestAtFirst(openSet, fScore);
            GridCell current = openSet.get(0);
            if (current == problem.finalPoint) {
                return reconstructPath(cameFrom, current);
            }
            openSet.remove(0);
            for (GridCell neighbour: getNeighbours(current, problem.map)) {
                double tentativeGScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + DISTANCE_BETWEEN_NODES;
                if (tentativeGScore < gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeGScore);
                    fScore.put(neighbour, tentativeGScore + heuristicFunction(neighbour, problem.finalPoint));
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

    private static void putLowestAtFirst(List<GridCell> set, Map<GridCell, Double> fScore) {
        Collections.sort(set, (a, b) -> Double.compare(fScore.getOrDefault(a, Double.POSITIVE_INFINITY), fScore.getOrDefault(b, Double.POSITIVE_INFINITY)));
    }

    private static double calculateBestGuess(GridCell node, GridCell finalPoint, Map<GridCell, Double> gScore) {
        double f = gScore.get(node) + heuristicFunction(node, finalPoint);
        return f;
    }

    private static List<GridCell> getNeighbours(GridCell node, Grid map) {
        List<GridCell> neighbours = new ArrayList<>();
        for (int i = node.posX - DISTANCE_BETWEEN_NODES; i <= node.posX + DISTANCE_BETWEEN_NODES; i++) {
            for (int j = node.posY - DISTANCE_BETWEEN_NODES; j <= node.posY + DISTANCE_BETWEEN_NODES; j++) {
                if (i < 0 || j < 0 || i >= map.width || j >= map.height)
                    continue;
                if (!map.positions[i][j].isObstacle)
                neighbours.add(map.positions[i][j]);
            }
        }
        return neighbours;
    }

    private static double heuristicFunction(GridCell node, GridCell finalPoint) {
        double h = Math.sqrt(Math.abs(node.posX - finalPoint.posX) + Math.abs(node.posY - finalPoint.posY));
        return h;
    }

    public static void main(String[] args) {
        // Grid grid = new Grid(15, 15, 0.25);
        // grid.print();
        // ShortestPathProblem problem = new ShortestPathProblem(grid);
        // List<GridCell> solution = solve(problem);
        // if (solution != null) {
        //     System.out.println("Solution:");
        //     grid.highlight(solution);
        //     grid.print();
        // }
        // else
        //     System.out.println("No solution :(");
        Grid grid = new Grid(15, 15);
        grid.addObstacle(5,6);
        grid.addObstacle(14,6);
        grid.addObstacle(10,6);
        grid.addObstacle(11,6);
        grid.addObstacle(9,5);
        grid.addObstacle(0,8);
        grid.addObstacle(1,8);
        grid.addObstacle(2,8);
        grid.addObstacle(3,8);
        grid.addObstacle(4,8);
        grid.addObstacle(5,8);    
        grid.addObstacle(6,8);
        grid.addObstacle(7,8);
        grid.addObstacle(8,8);
        grid.addObstacle(9,8);
        grid.addObstacle(9,7);
        grid.addObstacle(9,6);
        grid.addObstacle(9,3);
        grid.addObstacle(8,3);
        grid.addObstacle(7,3);
        grid.addObstacle(6,4);
        grid.addObstacle(6,5);
        grid.addObstacle(7,2);
        grid.addObstacle(7,3);
        grid.print();
        ShortestPathProblem problem = new ShortestPathProblem(grid);
        problem.setStartPoint(1,3);
        problem.setFinalPoint(1,12);
        List<GridCell> solution = solve(problem);
        if (solution != null) {
            System.out.println("Solution:");
            grid.highlight(solution);
            grid.print();
        }
        else
            System.out.println("No solution :(");
        
    }
}