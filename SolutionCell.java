package search_algorithm;

public class SolutionCell extends GridCell {
    public SolutionCell(int x, int y) {
        posX = x;
        posY = y;
        isObstacle = false;
    }

    public String toString() {
        return "[.]";
    }
}
