
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * . The Class of the high score animation
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable listScores;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.listScores = scores;
        this.stop = false;

    }
    // ...

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Image img = null;
        try {
            java.io.InputStream is = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("background_images/cup.jpg");
            img = ImageIO.read(is);
            d.drawImage(0, 0, img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int j = 30;
        d.setColor(Color.black);
        d.drawText(250, 100, "High Scores", 45);
        for (ScoreInfo s : this.listScores.getHighScores()) {
            d.drawText(175, 200 + j, s.getName(), 45);
            d.drawText(525, 200 + j, Integer.toString(s.getScore()), 45);
            j = j + 45;
        }
        d.setColor(Color.black);
        d.drawText(300, 500, "Press space to continue", 20);

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}