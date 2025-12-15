import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Line;

/**
 * Represents the game grid for the Snake game.
 *
 * <p>This class defines the number of rows and columns on the board,
 * provides methods to check whether a position is within bounds,
 * generates random positions for food, and draws the grid lines
 * on the canvas.</p>
 */
public class Grid {
    private int rows;
    private int cols;
    private Random random;

    /**
     * Constructs a grid with the specified number of rows and columns.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        random = new Random();
    }

     /**
     * Returns the number of rows in the grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }


    /**
     * Checks whether a given position is within the bounds of the grid.
     *
     * @param p the position to check
     * @return {@code true} if the position is within the grid; {@code false} otherwise
     */
    public boolean inBounds(Position p) {

        return p.row >= 0 && p.row < rows && p.col >= 0 && p.col < cols;
    }

     /**
     * Generates a random position within the bounds of the grid.
     *
     * @return a new {@link Position} object with a random row and column
     */
    public Position randomPosition() {
        int r = random.nextInt(rows);
        int c = random.nextInt(cols);
        return new Position(r, c);
    }


    /**
     * Draws the grid lines on the provided canvas.
     *
     * <p>Vertical and horizontal lines are drawn in a dark gray color.
     * The cell size determines the spacing between lines.</p>
     *
     * @param canvas the CanvasWindow where the grid will be drawn
     * @param cellSize the size (in pixels) of each grid cell
     */
    public void draw(CanvasWindow canvas, int cellSize) {

        Color lineColor = new Color(50,50,50);

        // Draw vertical lines
        for (int c = 0; c <= cols; c++) {
            int x = c * cellSize;
            Line line = new Line(x, 0, x, rows * cellSize);
            line.setStrokeColor(lineColor);
            canvas.add(line);
        }

        // Draw horizontal lines
        for (int r = 0; r <= rows; r++){
            int y = r * cellSize;
            Line line = new Line(0, y, cols * cellSize, y);
            line.setStrokeColor(lineColor);
            canvas.add(line);
        }
    }

}
