import java.awt.Color;

/**
 * Represents a single segment of the snake's body.
 *
 * <p>Each segment has a position on the grid and a color for rendering.
 * The snake is composed of a linked sequence of these segments.</p>
 */
public class Segment {
    public Position pos;
    public Color color;

    /**
     * Constructs a new Segment with the specified position and color.
     *
     * @param pos the position of the segment on the grid
     * @param color the color to display the segment
     */
    public Segment(Position pos, Color color) {
        this.pos = pos;
        this.color = color;

    }
}
