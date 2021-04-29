/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
/**
 * .
 * The interface of block creator, which is the type in the block factory
 *
 */
public interface BlockCreator {
    /**
     * .
     * Create a block at the specified location
     *
     * @param x block's x
     * @param y block's y
     * @return the block
     */
    Block create(int x, int y);
}
