/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
/**
 * . Decorates block with width
 */
public class WidthBlock extends BlockDecorator {

    private int width;

    /**
     * . Constructor
     *
     * @param width     w
     * @param decorated the block
     */
    public WidthBlock(int width, BlockCreator decorated) {
        super(decorated);
        this.width = width;
    }

    /**
     * . Create a block at the specified location
     *
     * @param x - the x.
     * @param y - the y.
     * @return the block
     */
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setWidth(this.width);
        return b;
    }

}
