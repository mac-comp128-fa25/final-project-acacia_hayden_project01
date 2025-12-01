import java.util.Random;

public class Grid {
    private int rows;
    private int cols;
    private Random random;

    public Grid(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        random = new Random();
    }

    public int getRows() {
        return rows;

    }

    public int getCols() {
        return cols;
    }

    
    public boolean inBounds(Position p) {
        
        return p.row >= 0 && p.row < rows && p.col >= 0 && p.col < cols;
    }

    public Position randomPosition() {


        int r = random.nextInt(rows);
        int c = random.nextInt(cols);

        return new Position(r, c);
    }
}
