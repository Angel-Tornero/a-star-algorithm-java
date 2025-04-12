package a_star_algorithm;

public class GridCell {
    int posX;
    int posY;
    boolean isObstacle;

    public GridCell() {
        isObstacle = false;
    }

    public GridCell(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.isObstacle = false;
    }

    public String toString() {
        return "[ ]";
    }
}
