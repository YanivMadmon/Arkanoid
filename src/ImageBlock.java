
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Image;

/**
 * . block with Image
 */
public class ImageBlock extends BlockDecorator {

    private Image img;
    private int life;

    /**
     * . Contsructor
     *
     * @param image     the image
     * @param life      the appropriate life for this image
     * @param decorated block
     * @throws Exception if image's load fail
     */
    public ImageBlock(Image image, int life, BlockCreator decorated) throws Exception {
        super(decorated);
        this.img = image;
        this.life = life;
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
        b.setImage(this.life, this.img);
        return b;
    }

}
