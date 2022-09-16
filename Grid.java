package search_algorithm;
import java.util.List;

public class Grid {
    GridCell[][] positions;
    int width;
    int height;

    public Grid(int rows, int columns, double obstacleRate) {
        width = columns;
        height = rows;
        positions = new GridCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Math.random() <= obstacleRate)
                    positions[i][j] = new ObstacleCell(i, j);
                else
                    positions[i][j] = new GridCell(i, j);
            }
        }
    }
    public Grid(int rows, int columns) {
        width = columns;
        height = rows;
        positions = new GridCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                positions[i][j] = new GridCell(i, j);
            }
        }
    }

    public void addObstacle(int posX, int posY) {
        positions[posX][posY] = new ObstacleCell(posX, posY);
    }

    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(positions[i][j]);
            }
            System.out.print('\n');
        }
    }

    public GridCell getPosition(int posX, int posY) {
        return positions[posX][posY];
    }

    public void highlight(List<GridCell> solution) {
        for (GridCell node: solution) {
            positions[node.posX][node.posY] = new SolutionCell(node.posX, node.posY);
        }
    }

    public static void main(String args[]) {
        Grid grid = new Grid(5, 10, 0.25);
        grid.print();
    }
}