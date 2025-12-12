import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;


import org.junit.jupiter.api.Test;

public class SnakeGameTest {

    @Test 
    public void testSnakeInitialPosition() {
        Snake snake = new Snake(5,5);
        Segment head = snake.getHead();
        assertEquals(5, head.pos.row);
        assertEquals(5, head.pos.col);
    }

    @Test 
    public void testSnakeMove() {
        Snake snake = new Snake(5,5);
        snake.setVelocity(1,0);
        snake.move();

        Segment head = snake.getHead();
        assertEquals(5, head.pos.row);
        assertEquals(6, head.pos.col);
    }

    @Test 
    public void testSnakeGrow() {
        Snake snake = new Snake(5,5);
        snake.grow(Color.GREEN);
        snake.move();

        assertEquals(2, snake.getBody().size());
    }

    @Test
    public void testCollision() {
        Snake snake = new Snake(5,5);

        snake.grow(Color.GREEN);
        snake.move();
        snake.grow(Color.BLUE);
        snake.move();
        snake.grow(Color.YELLOW);
        snake.move();


        snake.setVelocity(0, 1);
        snake.move();

        snake.setVelocity(-1, 0);
        snake.move();
        snake.grow(Color.ORANGE);

        snake.setVelocity(0, -1);
        snake.move();

        assertTrue(snake.checkCollision());
    }

    @Test
    public void testGridInBounds() {
        Grid grid = new Grid(20,20 );
        assertTrue(grid.inBounds(new Position(10, 10)));
        assertFalse(grid.inBounds(new Position(-1, 10)));
        assertFalse(grid.inBounds(new Position(20,20)));


    }


    @Test
    public void testGridRandomPosition() {
        Grid grid = new Grid(20, 20);
        Position p = grid.randomPosition();

        assertTrue(p.row >= 0 && p.row < 20);
        assertTrue(p.col >= 0 && p.col < 20);
    }
}
