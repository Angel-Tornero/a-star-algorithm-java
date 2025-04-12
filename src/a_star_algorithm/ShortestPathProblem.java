package a_star_algorithm;

public class ShortestPathProblem {
    GridMap map;
    GridCell startPoint;
    GridCell finalPoint;

    public ShortestPathProblem(GridMap problemMap) {
        map = problemMap;
        startPoint = map.getPosition(0, 0);
        finalPoint = map.getPosition(problemMap.height - 1, problemMap.width - 1);
    }

    public void setStartPoint(int posX, int posY) {
        startPoint = map.positions[posX][posY];
    }

    public void setFinalPoint(int posX, int posY) {
        finalPoint = map.positions[posX][posY];
    }

}
