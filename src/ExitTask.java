/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import biuoop.GUI;

/**
 * .
 * Quit from the game
 */
public class ExitTask implements Task<Void> {

    private GUI gui;

    /**
     * .
     * Constructor
     *
     * @param gui gui
     */
    public ExitTask(GUI gui) {
        this.gui = gui;
    }

    /**.
     * Close the gui and finish the run
     * @return null
     */
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
