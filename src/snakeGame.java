import edu.macalester.graphics.*;
import edu.macalester.graphics.events.*;

public class snakeGame {

    private CanvasWindow canvas;
    private GraphicsGroup snakeGroup;
    private Snake snake;

    public snakeGame() {
        canvas = new CanvasWindow("SnakeGame", 400, 400);
        snake = new Snake();
        snakeGroup = new GraphicsGroup();
        canvas.add(snakeGroup);
        draw();
    }

    public void draw() {
        snake.render(snakeGroup);
    }

    canvas.onKeyDown(event -> {
        
    })
}
