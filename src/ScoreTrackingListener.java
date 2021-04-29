/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
/**
 * .
 * A listener to change in the score
 * Changes when block hitted, block destroyed and level finished
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;

    /**
     * .
     * Constructor
     *
     * @param scoreCounter the counter that counts the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**.
    *
    * @param beingHit the block
    * @param hitter the ball
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() <= 1) {
           this.currentScore.increase(15);
       } else {
           this.currentScore.increase(5);
       }
   }
}