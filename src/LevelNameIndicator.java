/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;

import biuoop.DrawSurface;

/**
 * .
 * An Indicator of the Lives-Remaning
 */
public class LevelNameIndicator implements Sprite {
    private String name;
    private int x;
    private int y;

    /**
     * .
     * Constructor
     *
     * @param name the level name
     * @param x    - x coordinate
     * @param y    - y coordinate
     */
    public LevelNameIndicator(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        }    /**.
     * Draw the sprite to the screen
     * @param surface - the surface
     */
       public void drawOn(DrawSurface surface) {
           surface.setColor(Color.black);
           surface.drawText(x, y, "Level Name: " + this.name, 14);
     }
       /**.
        * Notify the sprite that time has passed
        * @param dt the dt value
        */
       public void timePassed(double dt) {
       }

    /**
     * .
     * Add the name to the game
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}

