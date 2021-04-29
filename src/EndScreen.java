
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * . ens screen
 */
public class EndScreen implements Animation {
    private boolean win;
    private int score;
    private int i;
    private long createdMillis = System.currentTimeMillis();
    private long timePerNum;

    /**
     * . Constructor
     *
     * @param k     the keyboard
     * @param win   - If the player win or lose
     * @param score - The score
     */
    public EndScreen(KeyboardSensor k, boolean win, int score) {
        this.win = win;
        this.score = score;
        this.i = 0;
        this.timePerNum = 500;
    }

    /**
     * . Does the animation
     *
     * @param d  the surface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        if (win) {
            List<java.awt.Color> colors = new ArrayList<java.awt.Color>();
            this.setColors(colors);
            d.setColor(colors.get(this.i));
            d.fillRectangle(0, 15, d.getWidth(), d.getHeight());
            d.setColor(Color.black);
            d.drawText(100, d.getHeight() / 2, "You Win!!! Your score is " + Integer.toString(this.score), 40);
            long nowMillis = System.currentTimeMillis();
            long passedTime = nowMillis - this.createdMillis;
            if (passedTime > this.timePerNum) {
                passedTime = 0;
                this.createdMillis = nowMillis;
                this.i++;
            }
            if (this.i == 6) {
                this.i = 0;
            }
        } else {

            d.setColor(Color.red);
            d.drawText(150, d.getHeight() / 2, "Game Over. Your score is " + score, 30);
        }
        d.setColor(Color.black);
        d.drawText(300, 500, "Press space to continue", 20);
    }

    /**
     * . Returns if the anim should stop
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * Sets colors.
     *
     * @param colors the colors
     */
    public void setColors(List<java.awt.Color> colors) {
        java.awt.Color c1 = java.awt.Color.magenta;
        colors.add(c1);
        java.awt.Color c2 = java.awt.Color.red;
        colors.add(c2);
        java.awt.Color c3 = java.awt.Color.yellow;
        colors.add(c3);
        java.awt.Color c4 = java.awt.Color.green;
        colors.add(c4);
        java.awt.Color c5 = java.awt.Color.blue;
        colors.add(c5);
        java.awt.Color c6 = java.awt.Color.pink;
        colors.add(c6);
        java.awt.Color c7 = java.awt.Color.cyan;
        colors.add(c7);

    }
}
