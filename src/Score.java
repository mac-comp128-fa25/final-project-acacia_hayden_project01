import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import java.awt.Color;

/**
 * Tracks and displays the player's score in the Snake game.
 *
 * <p>This class manages the numeric score and its visual representation
 * on the game canvas. It provides methods to increment, reset, and
 * update the score, as well as to display a game-over message.</p>
 */
public class Score {
    private int score;
    private GraphicsText scoreText;
    private CanvasWindow canvas;

    /**
     * Constructs a new Score object and displays the initial score on the canvas.
     *
     * @param canvas the CanvasWindow where the score will be drawn
     * @param x the x-coordinate for the score text
     * @param y the y-coordinate for the score text
     */
    public Score(CanvasWindow canvas, int x, int y) {
        this.canvas = canvas;
        score = 0;
        scoreText = new GraphicsText("Score: 0", x, y);
        scoreText.setFillColor(Color.WHITE);
        scoreText.setFontSize(18);
        canvas.add(scoreText);
    }

    /**
     * Increments the score by one and updates the display.
     */
    public void increment() {
        score ++;
        updateText();
    }

    /**
     * Returns the current score.
     *
     * @return the current numeric score
     */
    public int getScore() {
        return score;
    }

     /**
     * Resets the score to zero and updates the display.
     */
    public void reset() {
        score = 0;
        scoreText.setText("Score: 0");
    }

    /**
     * Updates the displayed score text to match the current score.
     */
    public void updateText() {
        scoreText.setText("Score:" + score);
    }

     /**
     * Displays a "GAME OVER" message on the canvas with the final score.
     *
     * <p>The message is centered horizontally and uses a larger font and red color
     * to indicate the game has ended.</p>
     */
    public void showGameOver() {
        GraphicsText gameOverText = new GraphicsText("GAME OVER! Final Score: " + score, 
        canvas.getWidth()/2-120, canvas.getHeight()/2);

        gameOverText.setFillColor(Color.RED);
        gameOverText.setFontSize(20);
        canvas.add(gameOverText);
    }
}
