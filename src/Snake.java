import java.util.LinkedList;
import java.awt.Color;
/**
 * Represents the snake in the Snake game.
 *
 * <p>The snake's body is stored as a {@link LinkedList} of {@link Segment}
 * objects, where the first element in the list is the head of the snake and
 * the last element is the tail. This data structure allows efficient updates
 * to the snake's body as it moves and grows.</p>
 *
 * <p>The snake maintains a velocity vector ({@code dx}, {@code dy}) that
 * determines its movement direction. Each move shifts the position of each
 * segment forward, with optional growth when food is consumed.</p>
 */
public class Snake {

    private LinkedList<Segment> body; 
    private int dx = 1;
    private int dy = 0;
    private boolean growing = false;
    private Color nextSegmentColor;

    /**
     * Constructs a new snake with a single body segment.
     *
     * @param row the initial row position of the snake's head
     * @param col the initial column position of the snake's head
     */
    public Snake(int row, int col) {
        body = new LinkedList<>();
        body.add(new Segment(new Position(row,col), Color.GREEN));
    }
 
    /**
     * Returns the head segment of the snake.
     *
     * @return the first {@link Segment} in the snake's body
     */
    public Segment getHead() {
        return body.getFirst();
    }

     /**
     * Returns the position of the snake's head.
     *
     * @return the {@link Position} of the head segment
     */
    public Position getHeadPos() {
        return body.getFirst().pos;
    }

    /**
     * Returns the full body of the snake.
     *
     * <p>This method exposes the underlying {@link LinkedList} of segments,
     * which may be used for rendering or collision checks.</p>
     *
     * @return the linked list representing the snake's body
     */
    public LinkedList<Segment> getBody() {
        return body;
    }

    /**
     * Moves the snake one step in its current direction.
     *
     * <p>The position of each segment is updated to follow the segment in
     * front of it, and a new head position is computed based on the current
     * velocity. If the snake is in a growing state, a new segment is added
     * to the tail.</p>
     */
    public void move(){
        Segment oldTail = body.getLast();
        Position oldTailPos = new Position(oldTail.pos.row, oldTail.pos.col);

        Segment head = getHead();
        Position newHeadPos = new Position(head.pos.row + dy, head.pos.col + dx);

   
        for (int i = body.size() - 1; i >= 1; i--) {
            body.get(i).pos = body.get(i - 1).pos;
        }

        head.pos = newHeadPos;


        if (growing) {
            body.addLast(new Segment(oldTailPos, nextSegmentColor));
            growing = false;
        }
    }

    /**
     * Causes the snake to grow on its next move.
     *
     * <p>The new segment will be added at the tail and will match the color
     * of the food that was consumed.</p>
     *
     * @param foodColor the color of the food eaten by the snake
     */
    public void grow(Color foodColor) {
        growing = true;
        nextSegmentColor = foodColor;
    }

    /**
     * Checks whether the snake's head has collided with its body.
     *
     * <p>This method compares the head's position to each body segment,
     * excluding the head itself.</p>
     *
     * @return {@code true} if the snake collides with itself; {@code false} otherwise
     */
    public boolean checkCollision() {
        Position head = getHeadPos();
        for (int i=1; i < body.size(); i++) {
            if (head.equals(body.get(i).pos)){
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the snake's movement direction.
     *
     * <p>The snake is prevented from reversing direction directly, which
     * would cause an immediate collision with itself.</p>
     *
     * @param newDX the new horizontal velocity
     * @param newDY the new vertical velocity
     */
    public void setVelocity(int newDX, int newDY) {
        if (dx == -newDX && dy == -newDY) {
            return;
        }
        dx = newDX;
        dy = newDY;
    }
}
