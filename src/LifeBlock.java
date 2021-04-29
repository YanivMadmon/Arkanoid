/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * . HealthBlock
 */
public class LifeBlock extends BlockDecorator {

    private int life;

    /**
     * . Constructor
     *
     * @param life      h
     * @param decorated block
     */
    public LifeBlock(int life, BlockCreator decorated) {
        super(decorated);
        this.life = life;
    }

    /**
     * . Create a block at the specified location
     *
     * @param x - block's x.
     * @param y - block's y.
     * @return the block
     */
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setHealth(this.life);
        return b;
    }

}
