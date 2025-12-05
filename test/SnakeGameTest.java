import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class SnakeGameTest {

    @Test 
    public void testSnakeInitialPosition() {
        Snake snake = new Snake(5,5);
        Position head = snake.getHead();
        assertEquals(5, head.row);
        assertEquals(5, head.col);
    }

    @Test 
    public void testSnakeMove() {
        Snake snake = new Snake(5,5);
        snake.setVelocity(1,0);
        snake.move();

        Position head = snake.getHead();
        assertEquals(5, head.row);
        assertEquals(6, head.col);
    }

    @Test 
    public void testSnakeGrow() {
        Snake snake = new Snake(5,5);
        snake.grow();
        snake.move();

        assertEquals(2, snake.getBody().size());
    }

    @Test
    public void testCollision() {
        Snake snake = new Snake(5,5);
        snake.grow();
        snake.move();

        snake.grow();
        snake.move();

        snake.grow();
        snake.move();

        snake.setVelocity(0, 1);
        snake.move();
        snake.setVelocity(-1, 0);
        snake.move();
        snake.setVelocity(0, -1);
        snake.move();

        assertTrue(snake.checkCollision());
    }


    @Test
    public void testGridRandomPosition() {
        Grid grid = new Grid(20, 20);
        Position p = grid.randomPosition();

        assertTrue(p.row >= 0 && p.row < 20);
        assertTrue(p.col >= 0 && p.col < 20);
    }
}
