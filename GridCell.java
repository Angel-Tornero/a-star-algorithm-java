package search_algorithm;

public class GridCell {
    int posX;
    int posY;
    boolean isObstacle;

    public GridCell() {
        isObstacle = false;
    }

    public GridCell(int x, int y) {
        posX = x;
        posY = y;
        isObstacle = false;
    }

    public String toString() {
        return "[ ]";
    }
}