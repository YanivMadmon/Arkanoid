
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private long createdMillis = System.currentTimeMillis();
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long timePerNum;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.stop = false;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds * 1000;
        this.timePerNum = (long) this.numOfSeconds / countFrom;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt the dt
     *
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.magenta);

        if (this.countFrom != 0) {
            d.drawText(400, 350, Integer.toString(this.countFrom), 70);
        } else {
            this.stop = true;
        }
        long nowMillis = System.currentTimeMillis();
        long passedTime = nowMillis - this.createdMillis;
        if (passedTime > this.timePerNum) {
            passedTime = 0;
            this.createdMillis = nowMillis;
            this.countFrom--;
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}