/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Pause Screen.
 */
public class PauseScreen implements Animation {

   /**.
    * Starts the pause screen
    * finished when space is pressed
    * @param d the surface
    * @param dt the dt
    */
   public void doOneFrame(DrawSurface d, double dt) {
      d.setColor(Color.BLACK);
      d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
   }
   /**.
    * Return if the method should stop
    * @return true or false
    */
   public boolean shouldStop() {
       return false;
       }
}
