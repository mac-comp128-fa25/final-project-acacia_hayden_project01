import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import java.awt.Color;

public class Score {
    private int score;
    private GraphicsText scoreText;
    private CanvasWindow canvas;

    public Score(CanvasWindow canvas, int x, int y) {
        this.canvas = canvas;
        score = 0;
        scoreText = new GraphicsText("Score: 0", x, y);
        scoreText.setFillColor(Color.WHITE);
        scoreText.setFontSize(18);
        canvas.add(scoreText);
    }

    public void increment() {
        score ++;
        updateText();
    }

    public int getScore() {
        return score;
    }

    public void updateText() {
        scoreText.setText("Score:" + score);
    }

    public void showGameOver() {
        GraphicsText gameOverText = new GraphicsText("GAME OVER! Final Score:" + score, 
        canvas.getWidth()/2-120, canvas.getHeight()/2);

        gameOverText.setFillColor(Color.RED);
        gameOverText.setFontSize(20);
        canvas.add(gameOverText);
    }
}
