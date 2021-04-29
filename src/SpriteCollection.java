/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * .
 * Sprite collection
 */
public class SpriteCollection {

private List<Sprite> list;

    /**
     * .
     * Construct the sprite collection list
     */
    public SpriteCollection() {
    this.list = new ArrayList<Sprite>();
    }

    /**
     * .
     * Add sprite to the sprite list
     *
     * @param s - The sprite
     */
    public void addSprite(Sprite s) {
   this.list.add(s);
   }

    /**
     * .
     * Remove sprite from the sprite list
     *
     * @param s - The sprite
     */
    public void removeSprite(Sprite s) {
       if (this.list.contains(s)) {
           this.list.remove(s);
       }
   }

    /**
     * .
     * Call timePassed() on all sprites.
     *
     * @param dt the dt value
     */
    public void notifyAllTimePassed(double dt) {
       for (int i = 0; i < this.list.size(); i++) {
           this.list.get(i).timePassed(dt);
       }
   }

    /**
     * .
     * Call drawOn(d) on all sprites
     *
     * @param d - The DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
       for (int i = 0; i < this.list.size(); i++) {
           this.list.get(i).drawOn(d);
       }
   }
}