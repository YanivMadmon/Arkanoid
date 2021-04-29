
/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * . A class of generic menu animation
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {

    private boolean stop;
    private KeyboardSensor ks;
    private AnimationRunner ar;
    private List<String> keys;
    private List<String> toPrint;
    private List<T> returnVals;
    private T status;
    private String title;
    private List<Boolean> isSub;
    private List<Menu<T>> subMenus;
    private boolean waste;

    /**
     * . Constructor
     *
     * @param title the title
     * @param ks    keyboard
     * @param ar    anim runner
     */
    public MenuAnimation(String title, KeyboardSensor ks, AnimationRunner ar) {
        this.stop = false;
        this.ks = ks;
        this.ar = ar;
        this.keys = new ArrayList<String>();
        this.toPrint = new ArrayList<String>();
        this.returnVals = new ArrayList<T>();
        this.title = title;
        this.isSub = new ArrayList<Boolean>();
        this.subMenus = new ArrayList<Menu<T>>();
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.blue.darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(102, 102, this.title, 50);
        d.setColor(Color.white);
        d.drawText(100, 100, this.title, 50);
        for (int i = 0; i < this.keys.size(); i++) {
            String text = "(" + this.keys.get(i) + ") " + this.toPrint.get(i);
            d.drawText(100, 200 + i * 50, text, 30);
        }
        for (int i = 0; i < this.returnVals.size(); i++) {
            String key = keys.get(i);
            if (this.ks.isPressed(key)) {
                while (this.ks.isPressed(key)) {
                    this.waste = true;
                }
                if (this.isSub.get(i)) {
                    this.ar.run(this.subMenus.get(i));
                    this.stop = true;
                    this.status = this.subMenus.get(i).getStatus();
                    ((MenuAnimation<T>) this.subMenus.get(i)).again();
                } else {
                    this.status = this.returnVals.get(i);
                    this.stop = true;
                }
                // return;
            }
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.subMenus.add(null);
        this.isSub.add(false);
        this.keys.add(key);
        this.toPrint.add(message);
        this.returnVals.add(returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.isSub.add(true);
        this.subMenus.add(subMenu);
        this.keys.add(key);
        this.toPrint.add(message);
        this.returnVals.add(null);
    }

    /**
     * . Resets the stop condition and the status
     */
    public void again() {
        this.stop = false;
        this.status = null;
    }

}
