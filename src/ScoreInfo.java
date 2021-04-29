/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.io.Serializable;

/**
 * .
 * <p>
 * The score info.
 */
public class ScoreInfo  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * .
     * Constructor
     *
     * @param name  name
     * @param score score
     */
    public ScoreInfo(String name, int score) {

       this.name = name;
       this.score = score;
   }

    /**
     * .
     * Return name
     *
     * @return n name
     */
    public String getName() {
       return this.name;
   }

    /**
     * .
     * Return score
     *
     * @return s score
     */
    public int getScore() {
       return this.score;
   }
}