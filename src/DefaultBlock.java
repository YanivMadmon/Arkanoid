/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * . Creates the block that the decorators will decorate it.
 */
public class DefaultBlock implements BlockCreator {

    /**
     * . Create default block
     *
     * @param x - block's x
     * @param y - block's y
     * @return the block
     */
    public Block create(int x, int y) {
        Block b = new Block(x, y);
        return b;
    }

}
