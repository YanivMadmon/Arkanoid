/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;

/**
 * .
 * Parse color
 */
public class ColorsParser {

    /**
     * .
     * parse color definition and return the specified color.
     *
     * @param colorName name
     * @return the specified color.
     * @throws Exception if name is illegal
     */
    public static java.awt.Color colorFromString(String colorName) throws Exception {
        if (colorName.startsWith("RGB(")) {
            String nums = colorName.substring("RGB(".length(), colorName.length() - 1);
            String[] separate = nums.split(",");

            //Checks if there are R&G&B
            if (separate.length != 3) {
                System.out.println(" no 3 params");
                throw new Exception("no 3 params");
            }
            int r1 = Integer.parseInt(separate[0]);
            int r2 = Integer.parseInt(separate[1]);
            int r3 = Integer.parseInt(separate[2]);
            return new Color(r1, r2, r3);
        }

        colorName = colorName.toLowerCase();
        switch (colorName) {
        case "yellow":
            return Color.yellow;
        case "red":
            return Color.red;
        case "white":
            return Color.white;
        case "orange":
            return Color.orange;
        case "pink":
            return Color.pink;
        case "green":
            return Color.green;
        case "lightgray":
            return Color.lightGray;
        case "gray":
            return Color.gray;
        case "black":
            return Color.black;
        case "blue":
            return Color.blue;
        case "cyan":
            return Color.cyan;
        default:
            throw new Exception("Color not exist");
        }
    }
 }