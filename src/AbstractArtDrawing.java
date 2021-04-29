
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * Abstract Art Drawing.
 *
 * create random lines and drawing them.
 *
 */
public class AbstractArtDrawing {
    /**
     * random array of lines.
     *
     * create random lines an put them in array.
     *
     * @param linesArray - array of lines.
     * @param d          - DrawSurface.
     */
    public static void linesArray(Line[] linesArray, DrawSurface d) {
        for (int i = 0; i < 10; i++) {
            linesArray[i] = generateRandomLine(d);
        }
    }

    /**
     * random line.
     *
     * create one random line.
     *
     * @param surface - DrawSurface.
     * @return randLine - random line.
     */
    public static Line generateRandomLine(DrawSurface surface) {
        int x1 = (int) (Math.random() * 500) + 1;
        int y1 = (int) (Math.random() * 500) + 1;
        int x2 = (int) (Math.random() * 500) + 1;
        int y2 = (int) (Math.random() * 500) + 1;
        surface.setColor(java.awt.Color.black);
        surface.drawLine(x1, y1, x2, y2);
        Line randLine = new Line(x1, y1, x2, y2);
        return randLine;
    }

    /**
     * draw middle point.
     *
     * draw middle point of the line.
     *
     * @param linesArray - array of lines.
     * @param surface    - DrawSurface.
     */
    public static void drawMid(Line[] linesArray, DrawSurface surface) {
        for (int i = 0; i < 10; i++) {
            Point mid = linesArray[i].middle();
            surface.setColor(java.awt.Color.blue);
            surface.fillCircle((int) mid.getX(), (int) mid.getY(), 3);
        }
    }

    /**
     * draw intersection point.
     *
     * draw the intersection point of all the lines in the array.
     *
     * @param linesArray - array of lines.
     * @param surface    - DrawSurface.
     */
    public static void drawPointInter(Line[] linesArray, DrawSurface surface) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    Point inter = linesArray[i].intersectionWith(linesArray[j]);
                    if (inter != null) {
                        surface.setColor(java.awt.Color.red);
                        surface.fillCircle((int) inter.getX(), (int) inter.getY(), 3);
                    }
                }

            }
        }
    }

    /**
     * main.
     *
     * create array of random lines , draw lines , intersection and middle points.
     *
     * @param args - arguments from the user.
     */
    public static void main(String[] args) {
        Line[] linesArray = new Line[10];
        GUI gui = new GUI("Balls Test 1", 500, 500);
        DrawSurface d = gui.getDrawSurface();
        linesArray(linesArray, d);
        drawMid(linesArray, d);
        drawPointInter(linesArray, d);
        gui.show(d);
    }

}
