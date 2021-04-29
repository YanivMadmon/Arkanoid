/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * .
 * The HitNotifer interface
 */
public interface HitNotifier {
    /**
     * .
     * Add hl as a listener to hit events.
     *
     * @param hl listener
     */
    void addHitListener(HitListener hl);

    /**
     * .
     * Remove hl as a listener from the hit events.
     *
     * @param hl listener
     */
    void removeHitListener(HitListener hl);
}