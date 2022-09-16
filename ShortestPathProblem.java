package search_algorithm;

public class ShortestPathProblem {
    Grid map;
    GridCell startPoint;
    GridCell finalPoint;

    public ShortestPathProblem(Grid problemMap) {
        map = problemMap;
        startPoint = map.getPosition(0, 0);
        finalPoint = map.getPosition(problemMap.height - 1, problemMap.width - 1);
    }

    public static void main(String args[]) {
        Grid grid = new Grid(10, 10, 0.25);
        ShortestPathProblem problem = new ShortestPathProblem(grid);
        problem.map.print();
    }

    public void setStartPoint(int posX, int posY) {
        startPoint = map.positions[posX][posY];
    }

    public void setFinalPoint(int posX, int posY) {
        finalPoint = map.positions[posX][posY];
    }
}
