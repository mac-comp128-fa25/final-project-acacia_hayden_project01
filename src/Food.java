import java.awt.Color;

/**
 * Represents a food item on the Snake game grid.
 *
 * <p>Each food object has a position on the grid and a color used for
 * rendering. The snake can consume food to grow and increase the score.</p>
 */
public class Food {

    public Position position;
    public Color color;

    /**
     * Constructs a new Food object at the specified position with the given color.
     *
     * @param position the position of the food on the grid
     * @param color the color to display the food
     */
    public Food(Position position, Color color) {
        this.position = position;
        this.color = color;
    }
}
