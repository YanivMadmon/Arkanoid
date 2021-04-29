/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * .
 * <p>
 * A class of game managment
 * Contains the background and the field of the game,
 * including blocks and bounds information.
 * Has methods of running the game
 */
public class GameLevel implements Animation {

       private SpriteCollection sprites;
       private GameEnvironment environment;
       private Counter remaningBlocks;
       private AnimationRunner runner;
       private boolean running;
       private KeyboardSensor keyboard;
       private LevelInformation lvlInfo;
       private int width;
       private int height;
       private Counter remaningBalls;
       private Counter score;
       private Counter lifeRemaning;
       private Paddle paddle;

    /**
     * .
     * Create a Game manager
     *
     * @param lvlInfo      - The info of the level
     * @param ks           - the Keyboard
     * @param ar           - the animation runner
     * @param score        - the score (even from before)
     * @param lifeRemaning - lives remaning
     * @param width        - game Width
     * @param height       - game Height
     */
    public GameLevel(LevelInformation lvlInfo, KeyboardSensor ks,
               AnimationRunner ar, Counter score, Counter lifeRemaning, int width, int height) {
           this.lvlInfo = lvlInfo;
           this.environment = new GameEnvironment();
           this.sprites = new SpriteCollection();
           this.remaningBlocks = new Counter(lvlInfo.numberOfBlocksToRemove());
           this.remaningBalls = new Counter(0);
           this.score = score;
           this.lifeRemaning = new Counter();
           this.runner = ar;
           this.keyboard = ks;
           this.lifeRemaning = lifeRemaning;
           this.height = height;
           this.width = width;
       }

    /**
     * .
     * Add a collidable to the collidable's list
     *
     * @param c = collidable
     */
    public void addCollidable(Collidable c) {
           this.environment.addCollidable(c);
       }

    /**
     * .
     * Remove a collidable from the collidable's list
     *
     * @param c = collidable
     */
    public void removeCollidable(Collidable c) {
           this.environment.removeCollidable(c);
       }

    /**
     * .
     * Add a sprite to the sprite's list
     *
     * @param s - sprite
     */
    public void addSprite(Sprite s) {
           this.sprites.addSprite(s);
       }

    /**
     * .
     * Remove a sprite from the sprite's list
     *
     * @param s - sprite
     */
    public void removeSprite(Sprite s) {
           this.sprites.removeSprite(s);
       }

    /**
     * .
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game
     *
     * @param horizontalBound
     * @param verticalBound
     * @param margin
     */
    public void initialize() {

            //Create BackGround
            this.sprites.addSprite(lvlInfo.getBackground());

            //Create Bounds
            Color color = Color.gray;
            Block indicatorsBlock = new Block(0, 0, this.width, 20, Color.white, Color.white, 0);
            Block b1 = new Block(0, 20, this.width, 25, color, color, 0);
            Block b2 = new Block(0, 20, 25, this.height, color, color, 0);
            Block b3 = new Block(this.width - 25, 20, 25,
                    this.height, color, color, 0);
            b1.addToGame(this);
            b2.addToGame(this);
            b3.addToGame(this);
            indicatorsBlock.addToGame(this);

            Block deathBlock = new Block(-this.width, this.height + 2 * 5,
                    this.width * 3, this.height, color, color, 0);
            deathBlock.addToGame(this);
            BallRemover ballR = new BallRemover(this, remaningBalls);
            deathBlock.addHitListener(ballR);

            indicators();

            Point p = new Point(this.width / 2 - this.lvlInfo.paddleWidth() / 2,
                    this.height - 25 - 15 + 1);
            Rectangle r = new Rectangle(p, this.lvlInfo.paddleWidth(), 15);
            this.paddle = new Paddle(r, Color.orange, this.width,
                    25, this.keyboard, this.lvlInfo.paddleSpeed());
            this.paddle.addToGame(this);

            ScoreTrackingListener scoreT = new ScoreTrackingListener(this.score);
            BlockRemover blockR = new BlockRemover(this, remaningBlocks);
              for (Block i : this.lvlInfo.blocks()) {
                  i.addHitListener(blockR);
                  i.addHitListener(scoreT);
                  i.addToGame(this);
              }
        }

    /**
     * .
     * Create the balls and the paddle, balls will be with random color
     */
    public void ballsAndPaddle() {
              for (Velocity i : lvlInfo.initialBallVelocities()) {
                  Ball b1 = new Ball(this.width / 2, this.height - 25 - 30, 5,
                          Color.white, this.environment);
                  b1.setVelocity(i);
                  b1.addToGame(this);
                this.remaningBalls.increase(1);
              }

              this.paddle.moveTo(this.paddle.getMiddle());
        }

    /**
     * .
     * Initiallize the indicators
     */
    public void indicators() {
            int distance = this.width - (2 * 25);
            int x = distance / 8;
            int y = 20 / 3 * 2;
            y += y / 2 - 1;

            LivesIndicator lIndic = new LivesIndicator(this.lifeRemaning, x + 10, y);
            lIndic.addToGame(this);

            ScoreIndicator sIndic = new ScoreIndicator(this.score, x * 4 + 10, y);
            sIndic.addToGame(this);

            LevelNameIndicator nameIndic = new LevelNameIndicator(this.lvlInfo.levelName(), x * 6 + 10, y);
            nameIndic.addToGame(this);
        }

    /**
     * .
     * Starts the Animation
     */
    public void playOneTurn() {
          this.ballsAndPaddle();
          this.runner.run(new CountdownAnimation(2, 3, this.sprites));

          this.running = true;
          this.runner.run(this);
        }

        /**.
         * Moves the animation one frame next
         * @param d the surface
         * @param dt the game dt value
         */
        public void doOneFrame(DrawSurface d, double dt) {


          this.sprites.drawAllOn(d);
          this.sprites.notifyAllTimePassed(dt);

          //Pause
          if (this.keyboard.isPressed("p")) {
              Animation pause = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                      new PauseScreen());
              this.runner.run(pause);
           }

          //When Win or Lose
              if (this.remaningBlocks.getValue() <= 0) {
                  score.increase(100);
                  this.running = false;
              }
              if (this.remaningBalls.getValue() <= 0) {
                  this.lifeRemaning.decrease(1);
                  this.running = false;
              }


         }
        /**.
         * Returns if the animation should stop
         * @return true or false
         */
        public boolean shouldStop() {
            return !this.running;
        }

    /**
     * .
     * Returns the number of blocks remaning
     *
     * @return num int
     */
    public int blocksRemaning() {
            return this.remaningBlocks.getValue();
        }

}
