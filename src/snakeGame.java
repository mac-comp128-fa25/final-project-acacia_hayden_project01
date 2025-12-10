import edu.macalester.graphics.CanvasWindow;

import edu.macalester.graphics.Rectangle;

import edu.macalester.graphics.events.Key;

import edu.macalester.graphics.events.KeyboardEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class SnakeGame {

    private static final int CELL_SIZE = 25;
    private static final int ROWS = 20;
    private static final int COLS = 20;

    private CanvasWindow canvas;
    private Snake snake;

    private Grid grid;


    private boolean gameOver = false;

    private double timeSinceMove = 0;
    private double moveDelay = 0.2;

    private Score score;
    private ArrayList<Food> foods = new ArrayList<>();


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Survival", COLS * CELL_SIZE, ROWS * CELL_SIZE);

        canvas.setBackground(Color.BLACK);

        grid = new Grid(ROWS, COLS);
        snake = new Snake(ROWS / 2, COLS / 2);
        score = new Score(canvas, 10, 20);

        spawnFood(5);

        draw();

        canvas.onKeyDown(event -> { 
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
        });
        canvas.animate(dt -> update(dt));
        
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
        boolean foodEaten = false;

        // check food collison
        Iterator<Food> it = foods.iterator();
        while (it.hasNext()) {
            Food f = it.next();
            if (head.equals(f.position)) {
                snake.grow();
                snake.setColor(f.color);// Snake turns the color of the food!
                score.increment();
                it.remove();
                foodEaten = true;
            }

        }
        if (foodEaten) {
            spawnFood(3);
        }


        if (!grid.inBounds(head) || snake.checkCollision()) {
            gameOver = true;
            score.showGameOver();
            return;
        }

        draw();
        
    }

    private void spawnFood(int count) {

        Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK};
        LinkedList<Position> body = snake.getBody();


        for (int i = 0; i < count; i++) {
            Position p;
            boolean valid;

            do {
                p = grid.randomPosition();
                valid = true;

                for (Position s : body) {
                    if (s.equals(p)) {
                        valid = false;
                        break;
                    }
                }

                for (Food f : foods) {
                    if (f.position.equals(p)) {
                        valid = false;
                        break;
                    }
                }

            } while (!valid);

            Color c = colors[(foods.size() + i) % colors.length];

            foods.add(new Food(p,c));

        }
    }


    private void draw() {

        canvas.removeAll();
        grid.draw(canvas, CELL_SIZE);

        for (Food f : foods) {
            Rectangle foodRect = new Rectangle(f.position.col * CELL_SIZE, f.position.row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            foodRect.setFilled(true);
            foodRect.setFillColor(f.color);
            canvas.add(foodRect);
        }

        for (Position part : snake.getBody()) {
            Rectangle r = new Rectangle(part.col * CELL_SIZE,part.row * CELL_SIZE,CELL_SIZE,CELL_SIZE);
            r.setFilled(true);
            r.setFillColor(snake.getColor());

            canvas.add(r);

        }

    }




    public static void main(String[] args) {
        
        new SnakeGame();
        
    }


}
