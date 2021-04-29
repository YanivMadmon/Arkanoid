/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Ball.
 *
 * @author Yaniv Madmon  class ball
 */
public class Ball implements Sprite {


    private GameEnvironment env;
    private Velocity velocity;
    private double radius;
    private Point point;
    private Color color;

    /**
     * .
     * Create Ball
     *
     * @param point middle point of the circle
     * @param r     radius
     * @param color color of the circle
     * @param env   - The game Environment
     */
    public Ball(Point point, double r, Color color, GameEnvironment env) {
        this.point = point;
        this.color = color;
        this.radius = r;
        this.velocity = new Velocity(5, 5);
        this.env = env;
      }

    /**
     * .
     * Create Ball from coordinate
     *
     * @param x     Center x's
     * @param y     Center y's
     * @param r     radius
     * @param color Circle's color
     * @param env   - The game Environment
     */
    public Ball(double x, double y, double r, Color color, GameEnvironment env) {
            Point p = new Point(x, y);
            this.point = p;
            this.color = color;
            this.radius = r;
            this.velocity = new Velocity(5, 5);
            this.env = env;
      }

    /**
     * .
     * Return x value
     *
     * @return the x of the middle point of the circle
     */
    public int getX() {
      return (int) Math.round(this.point.getX());
      }

    /**
     * .
     * Return y value
     *
     * @return the y of the middle point of the circle
     */
    public int getY() {
          return (int) Math.round(this.point.getY());
      }

    /**
     * .
     * Return radius size
     *
     * @return size size
     */
    public double getSize() {
          return this.radius;
      }

    /**
     * .
     * Return ball's color
     *
     * @return color color
     */
    public java.awt.Color getColor() {
          return this.color;
      }

    /**
     * .
     * Return the current postion of the ball (by returning it's center);
     *
     * @return the center point
     */
    public Point getCenter() {
         return this.point;
      }

    /**.
    * Draw the ball on the given DrawSurface
    * @param surface - the surface
    */
      public void drawOn(DrawSurface surface) {
          surface.setColor(color);
          surface.fillCircle((int) point.getX(), (int) point.getY(), (int) radius);
          surface.setColor(Color.black);
          surface.drawCircle((int) point.getX(), (int) point.getY(), (int) radius);
    }

    /**
     * .
     * Set the velocity by type of Velocity
     *
     * @param v - the velocity
     */
    public void setVelocity(Velocity v) {
          this.velocity = v;
      }

    /**
     * .
     * Set the velocity by dx and dy
     *
     * @param dx - x change
     * @param dy - y change
     */
    public void setVelocity(double dx, double dy) {
          this.velocity = new Velocity(dx, dy);;
      }

    /**
     * .
     * return ball's velocity
     *
     * @return velocity velocity
     */
    public Velocity getVelocity() {
          return this.velocity;
      }

    /**
     * .
     * Remove the ball from the game
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
          g.removeSprite(this);
      }

    /**
     * .
     * Move the ball one step forward
     * Check if the ball will hit some object. If it does, the velocity will be changed,
     * and the ball will stick to the line
     *
     * @param dt the dt value
     */
    public void moveOneStep(double dt) {
          Velocity velocityDt = this.velocity.setByDt(dt);
          Point p = velocityDt.applyToPoint(this.point);
          Point start = new Point((int) this.point.getX(), (int) this.point.getY());
          Point end = new Point((int) p.getX(), (int) p.getY());
          Line trajectory = new Line(start, end);
          CollisionInfo info = this.env.getClosestCollision(trajectory);
          if (info != null) {
              Velocity newV = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
                  this.setVelocity(newV);
          }
          velocityDt = this.velocity.setByDt(dt);
          this.point = velocityDt.applyToPoint(this.point);
      }

      /**.
       * Update the movement of the ball
       *  @param dt the dt value
       */
      public void timePassed(double dt) {
          this.moveOneStep(dt);
          this.env.penetration(this);
      }

    /**
     * .
     * Add the ball to the game
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
          game.addSprite(this);
      }

}
