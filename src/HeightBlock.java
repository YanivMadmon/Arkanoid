/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * . Decorates block with height
 */
public class HeightBlock extends BlockDecorator {

    private int height;

    /**
     * . Constructor
     *
     * @param height    h
     * @param decorated block
     */
    public HeightBlock(int height, BlockCreator decorated) {
        super(decorated);
        // TODO Auto-generated constructor stub
        this.height = height;
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
        b.setHeight(this.height);
        return b;
    }

}
