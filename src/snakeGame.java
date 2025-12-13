import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Ellipse;

import edu.macalester.graphics.events.Key;

import edu.macalester.graphics.events.KeyboardEvent;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SnakeGame {

    private static final int CELL_SIZE = 40;
    private static final int ROWS = 15;
    private static final int COLS = 30;

    private static final double START_DELAY = 0.2;
    private CanvasWindow canvas;
    private Snake snake;

    private Grid grid;
    private Button restartButton;

    private boolean gameOver = false;

    private double timeSinceMove = 0;
    private double moveDelay = START_DELAY;

    private Score score;
    private ArrayList<Food> foods = new ArrayList<>();
    private GraphicsGroup snakeGroup;

    public SnakeGame() {
        canvas = new CanvasWindow("Snake Survival", COLS * CELL_SIZE, ROWS * CELL_SIZE);
        canvas.setBackground(Color.BLACK);

        grid = new Grid(ROWS, COLS);
        snake = new Snake(ROWS / 2, COLS / 2);
        score = new Score(canvas, 10, 20);

        snakeGroup = new GraphicsGroup();
        canvas.add(snakeGroup);

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

        Segment head = snake.getHead();
        if (!grid.inBounds(head.pos) || snake.checkCollision()) {
            gameOver = true;
            score.showGameOver();
            setUpRestart();
            return;
        }

        boolean foodEaten = false;
        Iterator<Food> it = foods.iterator();
        while (it.hasNext()) {
            Food f = it.next();
            if (snake.getHeadPos().equals(f.position)) {
                snake.grow(f.color);
                score.increment();
                it.remove();
                foodEaten = true;

                moveDelay = Math.max(0.07, moveDelay * 0.98);
            }
        }

        if (foodEaten) {
            spawnFood(2);
        }

        draw();
    }

    private void spawnFood(int count) {

        Color[] colors = {
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.YELLOW,
            Color.ORANGE,
            Color.PINK,
            Color.MAGENTA,
            Color.CYAN,
            Color.WHITE,
            Color.LIGHT_GRAY,
            Color.GRAY,
            new Color(128, 0, 128),
            new Color(0, 128, 128),
            new Color(0, 255, 128),
            new Color(255, 105, 180),
            new Color(255, 0, 255),
            new Color(0, 255, 255),
            new Color(255, 165, 0),
            new Color(173, 216, 230),
            new Color(144, 238, 144),
            new Color(240, 230, 140),
            new Color(210, 105, 30),
            new Color(255, 20, 147),
            new Color(75, 0, 130),
            new Color(127, 255, 212)
        };

        LinkedList<Segment> body = snake.getBody();

        for (int i = 0; i < count; i++) {
            Position p;
            boolean valid;

            do {
                p = grid.randomPosition();
                valid = true;

                for (Segment s : body) {
                    if (s.pos.equals(p)) {
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

            Color c = colors[(int)(Math.random() * colors.length)];
            foods.add(new Food(p, c));
        }
    }

    private void draw() {
        canvas.removeAll();
        grid.draw(canvas, CELL_SIZE);

        for (Food f : foods) {
            double size = CELL_SIZE * 0.7;
            double offset = (CELL_SIZE - size) / 2;

            Ellipse foodCircle = new Ellipse(
                f.position.col * CELL_SIZE + offset,
                f.position.row * CELL_SIZE + offset,
                size,
                size
            );
            foodCircle.setFilled(true);
            foodCircle.setFillColor(f.color);
            canvas.add(foodCircle);
        }

        for (Segment seg : snake.getBody()) {
            double x = seg.pos.col * CELL_SIZE;
            double y = seg.pos.row * CELL_SIZE;

            Rectangle r = new Rectangle(x, y, CELL_SIZE, CELL_SIZE);
            r.setFilled(true);
            r.setFillColor(seg.color);
            r.setStrokeColor(Color.BLACK);
            canvas.add(r);
        }
    }

    public void setUpRestart() {
        if (restartButton == null) {
            restartButton = new Button("Restart");
            restartButton.onClick(() -> restartGame());
        }
        canvas.add(restartButton);
        restartButton.setCenter(
            COLS * CELL_SIZE / 2,
            ROWS * CELL_SIZE / 2 + 50
        );
    }

    public void restartGame() {
        gameOver = false;
        timeSinceMove = 0;
        moveDelay = START_DELAY;

        snake = new Snake(ROWS / 2, COLS / 2);
        foods.clear();
        spawnFood(3);

        score.reset();

        if (restartButton != null) {
            canvas.remove(restartButton);
        }

        draw();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
