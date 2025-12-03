import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Line;

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

    public void draw(CanvasWindow canvas, int cellSize) {
        Color lineColor = new Color(50,50,50);
        for (int c = 0; c <= cols; c++) {
            Line line = new Line(c * cellSize, 0, c *cellSize, rows * cellSize);
            line.setStrokeColor(lineColor);
            canvas.add(line);
        }

        for (int r = 0; r <= rows; r++){
            Line line = new Line(0, r * cellSize, cols * cellSize, r * cellSize);
            line.setStrokeColor(lineColor);
            canvas.add(line);
        }

    }
}
