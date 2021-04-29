/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;

/**
 * .
 * This class will be in charge of creating the differnet levels,
 * and moving from one level to the next.
 */
public class GameFlow {

   private KeyboardSensor keyboardSensor;
   private HighScoresTable hST;
   private DialogManager dialog;
   private File filename;
   private AnimationRunner animationRunner;
   private Counter remaningLives;
   private Counter score;

    /**
     * .
     * Constructor
     *
     * @param ar       - The Animation Runner
     * @param ks       - The keboard
     * @param lives    - Lives remaning
     * @param hST      - HighScoresTable
     * @param d        - dialog manager
     * @param filename - filename
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, HighScoresTable hST,
           DialogManager d, File filename) {
       this.remaningLives = new Counter(lives);
       this.keyboardSensor = ks;
       this.animationRunner = ar;
       this.score = new Counter();
       this.hST = hST;
       this.dialog = d;
       this.filename = filename;
   }

    /**
     * .
     * Runs each level
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {

      for (LevelInformation levelInfo : levels) {
         GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                 this.animationRunner, this.score, this.remaningLives, 800, 600);

         level.initialize();

         while ((level.blocksRemaning() > 0) && (this.remaningLives.getValue() > 0)) {
             level.playOneTurn();
         }

         if (this.remaningLives.getValue() <= 0) {
            break;
         }

      }
      boolean updated = this.hST.updateTable(this.dialog, this.score.getValue());
      if (updated) {
          //Save the new hst
              try {
                this.hST.save(this.filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
      }
      boolean isWin = (remaningLives.getValue() > 0);
      EndScreen es = new EndScreen(this.keyboardSensor, isWin, this.score.getValue());
      Animation end = new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY, es);
      this.animationRunner.run(end);
      Animation hiscores = new HighScoresAnimation(this.hST);
      this.animationRunner.run(new KeyPressStoppableAnimation(
              this.keyboardSensor, this.keyboardSensor.SPACE_KEY, hiscores));
   }
}

