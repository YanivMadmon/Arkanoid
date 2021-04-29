
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 * . Background class
 *
 * @author PCP -RENT
 */
public class BackGround implements Sprite {

    private Color color;
    private boolean bool = false;
    private Image ima;

    /**
     * . Constructor
     *
     * @param ima ima
     */
    public BackGround(Image ima) {
        this.ima = ima;
    }

    /**
     * . Constructor
     *
     * @param c color
     */
    public BackGround(Color c) {
        this.color = c;
        this.bool = true;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (bool) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else {
            d.drawImage(0, 0, this.ima);
        }

    }

    @Override
    public void timePassed(double dt) {

    }

}
