/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
/**
 * .
 * The basic decorator of the block
 *
 *
 */
public abstract class BlockDecorator implements BlockCreator {

    private BlockCreator decorated;

    /**
     * The Constructor. It wraps any object of type BlockCreator.
     *
     * @param decorated the object to decorate
     */
    protected BlockDecorator(BlockCreator decorated) {
        this.decorated = decorated;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = this.decorated.create(xpos, ypos);
        return b;
    }

}
