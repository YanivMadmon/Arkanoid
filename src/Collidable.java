/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * .
 * <p>
 * An interface of collidable info
 */
public interface Collidable {
    /**
     * .
     * Returns the collision Rectangle
     *
     * @return the collision Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * .
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          - The ball that hit
     * @param collisionPoint  - the collision Point
     * @param currentVelocity - the current velocity
     * @return new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
     }
