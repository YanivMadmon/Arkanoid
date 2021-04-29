/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;


/**
 * .
 * Decorates block with color
 */
public class ColorBlockDecorator extends BlockDecorator {

    private int health;
    private Color color;

    /**
     * .
     * Constructor
     *
     * @param decorated the block
     * @param color     c
     * @param hit       the hit
     */
    public ColorBlockDecorator(BlockCreator decorated, Color color, int hit) {
        super(decorated);
        // TODO Auto-generated constructor stub
        this.color = color;
        this.health = hit;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = super.create(xpos, ypos);
        b.setColor(this.health, this.color);
        return b;
    }

}
