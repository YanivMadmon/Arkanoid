/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * .
 * A listener to hit event
 */
public interface HitListener {
   /**
    * .
    * <p>
    * This method is called whenever the beingHit object is hit.
    * The hitter parameter is the Ball that's doing the hitting.
    *
    * @param beingHit Block
    * @param hitter   Ball
    */
   void hitEvent(Block beingHit, Ball hitter);
}
