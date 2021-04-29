/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;

/**
 * .
 * Block Class
 */
public class Block implements Collidable, Sprite, HitNotifier {


    private Rectangle rec;
    private int life = 15;
    private List<HitListener> hitListeners;
    private Color color1;
    private boolean hasFrame = false;
    private Map<Integer, Image> fillImages;
    private Map<Integer, Color> fillColors;

    /**
     * .
     * Create Block from coordinate.
     * No Frame.
     *
     * @param x      - upperLeft  x
     * @param y      -upperLeft  y
     * @param width  - rectangle width
     * @param height - rectangle height
     * @param color  - rectangle's color
     * @param life   - life remaning
     */
    public Block(double x, double y, double width, double height, Color color, int life) {
     Point p = new Point(x, y);
     this.rec = new Rectangle(p, width, height);
     this.life = life;
     this.hitListeners = new ArrayList<HitListener>();
     this.color1 = Color.black;
     this.hasFrame = false;
     this.fillColors = new TreeMap<Integer, Color>();
     this.fillImages = new TreeMap<Integer, Image>();
 }

    /**
     * .
     * Create Block from coordinate and frame color
     *
     * @param x      - upperLeft corner's x
     * @param y      -upperLeft corner's y
     * @param width  - rectangle width
     * @param height - rectangle height
     * @param color  - rectangle's color
     * @param frame  - The block's frame color
     * @param life   - life remaning
     */
    public Block(double x, double y, double width, double height, Color color, Color frame, int life) {
    Point p = new Point(x, y);
    this.rec = new Rectangle(p, width, height);
    this.life = life;
    this.hitListeners = new ArrayList<HitListener>();
    this.color1 = frame;
    this.hasFrame = true;
    this.fillImages = new TreeMap<Integer, Image>();
    this.fillColors = new TreeMap<Integer, Color>();
    this.fillColors.put(0, color);
    }

    /**
     * .
     * New Constructor, uses mainly by BlockDecorator
     *
     * @param x the x
     * @param y the y
     */
    public Block(int x, int y) {
        this.rec = new Rectangle(new Point(x, y), 10, 10);
        this.life = 1;
        this.fillImages = new TreeMap<Integer, Image>();
        this.fillColors = new TreeMap<Integer, Color>();
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**.
       * Returns the block's rectangle
       * @return this.rec
       */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

     /**.
     *  Returns the new velocity after the hit
     *  Also notify the block that a hit occurred
     * @param hitter - The ball that hit
     * @param collisionPoint - the point of the collision
     * @param currentVelocity - the current velocity
     * @return the new velocity
     */
     public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
         this.notifyHit(hitter);
         this.life--;

         double dx = currentVelocity.getDx();
         double dy = currentVelocity.getDy();

         if (this.rec.getRight().containsPoint(collisionPoint)) {
             dx = Math.abs(dx);
         }
         if (this.rec.getLeft().containsPoint(collisionPoint)) {
             dx = -Math.abs(dx);
         }
         if (this.rec.getTop().containsPoint(collisionPoint)) {
             dy = -Math.abs(dy);
         }
         if (this.rec.getBot().containsPoint(collisionPoint)) {
             dy = Math.abs(dy);
         }
         Velocity v = new Velocity(dx, dy);
         return v;
     }
      /**.
        * Draw the block on the given DrawSurface
        * @param surface - the surface
        */
          public void drawOn(DrawSurface surface) {

              //From ass6
              int x = (int) this.rec.getUpperLeft().getX();
              int y = (int) this.rec.getUpperLeft().getY();
              int blockHealth = this.life;
              if (this.fillImages.containsKey(blockHealth)) {
                  surface.drawImage(x, y, this.fillImages.get(blockHealth));
              } else if (this.fillColors.containsKey(blockHealth)) {
                      surface.setColor(this.fillColors.get(blockHealth));
                      surface.fillRectangle(x, y, (int) this.rec.getWidth(), (int) this.rec.getHeight());
              } else if (this.fillImages.containsKey(0)) {
                  surface.drawImage(x, y, this.fillImages.get(0));
              } else  if (this.fillColors.containsKey(0)) {
                  surface.setColor(this.fillColors.get(0));
                  surface.fillRectangle(x, y, (int) this.rec.getWidth(), (int) this.rec.getHeight());
              }

              //Draws the frame
              if (this.hasFrame) {
              surface.setColor(this.color1);
              surface.drawRectangle(x, y, (int) this.rec.getWidth(), (int) this.rec.getHeight());
              }
        }
          /**.
           * Notify the sprite that time has passed
           * @param dt the dt value
           */
          public void timePassed(double dt) {
          }

    /**
     * .
     * Add the block to the game's coolidable list and sprite list
     *
     * @param game the game that contains the lists
     */
    public void addToGame(GameLevel game) {
            game.addSprite(this);
            game.addCollidable(this);
        }

    /**
     * .
     * Remove the block from the game's coolidable list and sprite list
     *
     * @param game the game that contains the lists
     */
    public void removeFromGame(GameLevel game) {
            game.removeCollidable(this);
            game.removeSprite(this);
        }

    /**
     * .
     * Notify that the block was hit
     *
     * @param hitter the ball
     */
    public void notifyHit(Ball hitter) {
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
               hl.hitEvent(this, hitter);
            }
        }

    /**
     * .
     * Return the Remaning Health Point
     *
     * @return hitpoints hit points
     */
    public int getHitPoints() {
            return this.life;
        }

        /**.
         * Add a HitListener to the block
         * @param l the listener
         */
        public void addHitListener(HitListener l) {
            if (l != null) {
                this.hitListeners.add(l);
            }
        }
        /**.
         * Removes a HitListener from the block
         * @param l the listener
         */
        public void removeHitListener(HitListener l) {
        this.hitListeners.remove(l);
        }

    /**
     * .
     * Set Block's life
     *
     * @param blockHealth life
     */
    public void setHealth(int blockHealth) {
            this.life = blockHealth;
        }

    /**
     * .
     * Set Block's height
     *
     * @param height height
     */
    public void setHeight(int height) {
            this.rec = new Rectangle(this.rec.getUpperLeft(), this.rec.getWidth(), height);
        }

    /**
     * .
     * Set Block's width
     *
     * @param width width
     */
    public void setWidth(int width) {
            this.rec = new Rectangle(this.rec.getUpperLeft(), width, this.rec.getHeight());
        }

    /**
     * .
     * Set Block's frame color
     *
     * @param c color
     */
    public void setFrame(Color c) {
            this.color1 = c;
            this.hasFrame = true;
        }

    /**
     * .
     * Set Img according to the life
     *
     * @param blockHealth h
     * @param img         i
     */
    public void setImage(int blockHealth, Image img) {
            this.fillImages.put(blockHealth, img);
        }

    /**
     * .
     * Set blockColor according to the life
     *
     * @param blockHealth h
     * @param color       c
     */
    public void setColor(int blockHealth, Color color) {
            this.fillColors.put(blockHealth, color);
        }
}