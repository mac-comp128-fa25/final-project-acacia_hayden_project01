import edu.macalester.graphics.CanvasWindow;

import edu.macalester.graphics.Rectangle;

import edu.macalester.graphics.events.Key;

import edu.macalester.graphics.events.KeyboardEvent;
import java.awt.Color;
import java.util.LinkedList;


public class SnakeGame {

    private static final int CELL_SIZE = 25;
    private static final int ROWS = 20;
    private static final int COLS = 20;

    private CanvasWindow canvas;
    private Snake snake;

    private Grid grid;

    private Position food;

    private boolean gameOver = false;

    private double timeSinceMove = 0;
    private double moveDelay = 0.2;



    public SnakeGame() {
        canvas = new CanvasWindow("Snake Survival", COLS * CELL_SIZE, ROWS * CELL_SIZE);

        canvas.setBackground(Color.BLACK);

        grid = new Grid(ROWS, COLS);
        snake = new Snake(ROWS / 2, COLS / 2);

        spawnFood();

        draw();

        canvas.onKeyDown(e -> handleKey(e));
        canvas.animate(dt -> update(dt));
        }


    private void handleKey(KeyboardEvent event) {
        Key key = event.getKey();

        if (key == Key.UP_ARROW) {
            snake.setVelocity(0, -1);


        } else if (key == Key.DOWN_ARROW) {


            snake.setVelocity(0, 1);

        } else if (key == Key.LEFT_ARROW) {

            snake.setVelocity(-1, 0);

        } else if (key == Key.RIGHT_ARROW) {
            snake.setVelocity(1, 0);
        }
    }


    private void update(double dt) {

        if (gameOver) return;

        timeSinceMove += dt;


        if (timeSinceMove >= moveDelay) {
            timeSinceMove = 0;

            step();
        }
    }


    private void step() {

        snake.move();
        Position head = snake.getHead();

        if (!grid.inBounds(head)) {
            gameOver = true;
            return;
        }

        //self collision added
        if (snake.checkCollision()) {
            gameOver = true;
            return;
        }

        // still need to add self collision later
        if (head.equals(food)) {
            snake.grow();
            spawnFood();
        }

        draw();
    }


    private void spawnFood() {
        Position p;
        LinkedList<Position> body = snake.getBody();

        do {
            p = grid.randomPosition();
        } while (body.contains(p));

        food = p;
    }


    private void draw() {

        canvas.removeAll();

        for (Position part : snake.getBody()) {
            Rectangle r = new Rectangle(
                    part.col * CELL_SIZE,
                    part.row * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE
            );

            r.setFilled(true);
            r.setFillColor(Color.GREEN);

            canvas.add(r);

        }

        Rectangle foodRect = new Rectangle(
                food.col * CELL_SIZE, food.row * CELL_SIZE, CELL_SIZE, CELL_SIZE
              
        );
        foodRect.setFilled(true);
        foodRect.setFillColor(Color.RED);

        canvas.add(foodRect);
    }




    public static void main(String[] args) {
        
        new SnakeGame();
        
    }


}
