/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.util.Map;
import java.util.TreeMap;


/**
 * .
 * A class that contains information about the block
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> space;
    private Map<String, BlockCreator> blockC;

    /**
     * .
     * Constructor
     */
    public BlocksFromSymbolsFactory() {
        this.space = new TreeMap<String, Integer>();
        this.blockC = new TreeMap<String, BlockCreator>();
    }

    /**
     * .
     * returns true if 'str' is a valid space symbol.
     *
     * @param str string
     * @return true /false
     */
    public boolean isSpaceSymbol(String str) {
      if (this.space.containsKey(str)) {
          return true;
      }
      return false;
    }

    /**
     * .
     * returns true if 'str' is a valid block symbol
     *
     * @param str str
     * @return true if 'str' is a valid block symbol
     */
    public boolean isBlockSymbol(String str) {
        if (this.blockC.containsKey(str)) {
            return true;
        }
        return false;
    }

    /**
     * .
     * Return a block according to the definitions associated
     * with symbol str. The block will be located at position (xpos, ypos).
     *
     * @param str symbol
     * @param x   x'str
     * @param y   y'str
     * @return the new block
     */
    public Block getBlock(String str, int x, int y) {
        return this.blockC.get(str).create(x, y);
     }


    /**
     * .
     *
     * @param str symbol
     * @return the width in pixels associated with the given spacer-symbol
     */
    public int getSpaceWidth(String str) {
        return this.space.get(str);
     }

    /**
     * .
     * add spacer to the list
     *
     * @param str symbol
     * @param x   width
     */
    public void addSpaceWidth(String str, Integer x) {
        this.space.put(str, x);
    }

    /**
     * .
     * add block
     *
     * @param str symbol
     * @param b   block
     */
    public void addBlockCreator(String str, BlockCreator b) {
        this.blockC.put(str, b);
    }

    /**
     * .
     * return the map of the keys and the block that should be created
     *
     * @return the map
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockC;
    }
}

