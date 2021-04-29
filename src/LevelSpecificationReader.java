/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * . Read the level information from a text
 */
public class LevelSpecificationReader {

    private int startX = 0;
    private int currentX = 0;
    private int startY = 0;
    private int currentY = 0;
    private int rowHeight = 0;
    private int currentRow = 0;
    private BlocksFromSymbolsFactory bF;
    private boolean appearBlockDefinitions = false;

    /**
     * . Read the levels's information from a text
     *
     * @param reader r
     * @return list of level information
     * @throws Exception if read fails
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws Exception {

        List<LevelInformation> allLvls = new ArrayList<LevelInformation>();

        BufferedReader is = null;
        try {
            is = new BufferedReader((reader));
            String line;

            TextLevel lvl = null;
            boolean blocksCharsMode = false;
            while ((line = is.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("#") || line.equals("")) {
                    continue;
                }

                switch (line) {
                case "START_LEVEL":
                    lvl = new TextLevel();
                    this.bF = new BlocksFromSymbolsFactory();
                    break;

                case "END_LEVEL":
                    allLvls.add(lvl);

                    // reset all params
                    this.currentY = 0;
                    this.currentX = 0;
                    this.currentRow = 0;
                    this.startX = 0;
                    this.startY = 0;
                    this.rowHeight = 0;
                    lvl = null;
                    this.bF = null;
                    this.appearBlockDefinitions = false;
                    break;

                // For blocks part
                case "START_BLOCKS":
                    blocksCharsMode = true;
                    break;
                case "END_BLOCKS":
                    blocksCharsMode = false;
                    // System.out.println("MODE IS OFF");
                    break;

                // For others:
                default:
                    // When reading the chars of the blocks
                    if (blocksCharsMode) {
                        if (!this.appearBlockDefinitions) {
                            throw new RuntimeException("you didn't enter blockDefinitions path");
                        }
                        this.currentY = this.startY + (this.rowHeight * this.currentRow);
                        this.currentX = this.startX;
                        for (int i = 0; i < line.length(); i++) {
                            String symb = String.valueOf(line.charAt(i));
                            if (bF.isBlockSymbol(symb)) {
                                Block block = bF.getBlock(symb, this.currentX, this.currentY);
                                lvl.addBlock(block);
                                this.currentX += (int) block.getCollisionRectangle().getWidth();
                            } else if (bF.isSpaceSymbol(symb)) {
                                int width = bF.getSpaceWidth(symb);
                                this.currentX += width;
                            } else {
                                throw new RuntimeException("Error in symbol" + symb);
                            }
                        }
                        this.currentRow++;
                        continue;
                    }

                    levelBuilder(line, lvl);
                    break;
                // End switch
                }
                // End while
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
        return allLvls;
    }

    /**
     * . Gather the blocks-part in the level reader
     *
     * @param key   k
     * @param value v
     * @return true if one of these paramters has been read
     */
    public boolean blockBuilder(String key, String value) {
        switch (key) {
        case "blocks_start_x":
            if (Integer.parseInt(value) < 0) {
                throw new RuntimeException("blocks_start_x must be >= 0");
            }
            this.startX = Integer.parseInt(value);
            return true;
        case "blocks_start_y":
            if (Integer.parseInt(value) < 0) {
                throw new RuntimeException("blocks_start_y must be >= 0");
            }
            this.startY = Integer.parseInt(value);
            return true;
        case "row_height":
            if (Integer.parseInt(value) <= 0) {
                throw new RuntimeException("row_height must be positive");
            }
            this.rowHeight = Integer.parseInt(value);
            return true;
        case "block_definitions":
            this.appearBlockDefinitions = true;
            try {
                InputStreamReader iSR = new InputStreamReader(
                        ClassLoader.getSystemClassLoader().getResourceAsStream(value));
                this.bF = BlocksDefinitionReader.fromReader(iSR);
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        default:
            return false;
        }
    }

    /**
     * . Gather the parameters the relevant to the level itself, such as paddle, and
     * handle them.
     *
     * @param line line
     * @param lvl  text level
     * @throws Exception if color parser fails
     */
    public void levelBuilder(String line, TextLevel lvl) throws Exception {
        String[] separate = line.split(":");
        if (separate.length != 2) {
            throw new RuntimeException("Params are not good at line: " + line);
        }
        String key = separate[0];
        String value = separate[1];
        if (blockBuilder(key, value)) {
            return;
        }
        switch (key) {
        case "level_name":
            lvl.setLevelName(value);
            break;
        case "paddle_speed":
            lvl.setPaddleSpeed(Integer.parseInt(value));
            break;
        case "paddle_width":
            lvl.setPaddleWidth(Integer.parseInt(value));
            break;
        case "num_blocks":
            lvl.setNumberOfBlocksToRemove(Integer.parseInt(value));
            break;

        case "background":
            // Image
            if (value.startsWith("image(")) {
                if (value.endsWith(")")) {
                    Image img = null;
                    String imgName = value.substring("image(".length(), value.length() - 1);
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imgName);
                        img = ImageIO.read(is);
                        BackGround bg = new BackGround(img);
                        lvl.setBackground(bg);
                    } catch (Exception e) {
                        throw new RuntimeException("Can't load background image:" + imgName);
                    }
                }
            } else {
                // COLOR
                if (value.startsWith("color(")) {
                    if (value.endsWith(")")) {
                        String colorName = value.substring("color(".length(), value.length() - 1);
                        Color color = ColorsParser.colorFromString(colorName);
                        BackGround bg = new BackGround(color);
                        lvl.setBackground(bg);
                    }
                }
            }
            break;
        case "ball_velocities":
            String[] velocities = value.split(" ");
            for (int i = 0; i < velocities.length; i++) {
                String[] devided = velocities[i].split(",");
                if (devided.length != 2) {
                    throw new Exception("Velocity needs 2 parametrs");
                }
                double angle = Double.parseDouble(devided[0]);
                double speed = Double.parseDouble(devided[1]);

                if (speed <= 0) {
                    throw new Exception("Velocity needs 2 parametrs");
                }
                lvl.addInitialBallVelocities(Velocity.fromAngleAndSpeed(angle, speed));
            }
            lvl.setNnumberOfBalls(velocities.length);
            break;
        default:
            throw new Exception("Illegal key:" + key);

        }
    }

}
