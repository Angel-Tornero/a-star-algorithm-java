package a_star_algorithm;

public class ObstacleCell extends GridCell {
    public ObstacleCell(int x, int y) {
        posX = x;
        posY = y;
        isObstacle = true;
    }
    
    public String toString() {
        return "[X]";
    }
}
