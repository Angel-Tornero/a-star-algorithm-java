package a_star_algorithm;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a 15x15 grid
        GridMap grid = new GridMap(15, 15);

        // Add obstacles to the grid
        addObstacles(grid);

        // Set start and end points
        ShortestPathProblem problem = new ShortestPathProblem(grid);
        problem.setStartPoint(1, 3);
        problem.setFinalPoint(1, 12);

        // Run A* algorithm
        List<GridCell> solution = AStarSearchAlgorithm.solve(problem);

        // Print result
        if (solution != null) {
            System.out.println("Solution:");
            grid.highlight(solution);
            grid.print();
        } else {
            System.out.println("No solution :(");
        }
    }

    private static void addObstacles(GridMap grid) {
        int[][] obstacleCoords = {
            {5, 6}, {14, 6}, {10, 6}, {11, 6}, {9, 5},
            {0, 8}, {1, 8}, {2, 8}, {3, 8}, {4, 8},
            {5, 8}, {6, 8}, {7, 8}, {8, 8}, {9, 8},
            {9, 7}, {9, 6}, {9, 3}, {8, 3}, {7, 3},
            {6, 4}, {6, 5}, {7, 2}, {7, 3}
        };

        for (int[] coord : obstacleCoords) {
            grid.addObstacle(coord[0], coord[1]);
        }
    }
}
