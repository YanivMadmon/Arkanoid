/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
/**
 * .
 * Shows the high score of the game
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private boolean stop;

    /**
     * .
     * Constructor
     *
     * @param runner              runner
     * @param highScoresAnimation anim
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
       this.stop = false;
       this.runner = runner;
       this.highScoresAnimation = highScoresAnimation;
    }
    /**.
     * Run the anim
     * @return null
     */
    public Void run() {
       this.runner.run(this.highScoresAnimation);
       this.stop = true;
       return null;
    }

    /**
     * .
     * Return if it should stop
     *
     * @return stop boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
 }