import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * .
 *
 * @author Yaniv Madmon Main class
 */
public class Ass7Game {
    /**
     * .
     *
     * @param args the arguments
     * @throws IOException if load/save are not succssessfull
     */

    public static void main(String[] args) throws IOException {
        //Basic
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        File filename = new File("highscores");
        HighScoresTable highst = HighScoresTable.loadFromFile(filename);
        HighScoresAnimation hSA = new HighScoresAnimation(highst);
        Animation highscores = new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, hSA);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Arkanoid", keyboard, animationRunner);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Sets Levels", keyboard, animationRunner);
        Reader reader = null;
        InputStream input = null;
        List<LevelSetInfo> allSets = null;
        try {
            if (args.length != 0) {
                input = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            } else {
                input = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            }
           reader = new InputStreamReader(input);
           allSets = LevelSetReader.fromReader(reader);
        } catch (Exception e) {
            System.out.println("Could not read");
            e.printStackTrace();
        }
        for (LevelSetInfo set : allSets) {
            StartTask mode = new StartTask(animationRunner, set, keyboard, highst, gui.getDialogManager(), filename);
            subMenu.addSelection(set.getKey(), set.getDescription(), mode);
        }
        menu.addSubMenu("s", "start game", subMenu);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(animationRunner, highscores));
        menu.addSelection("q", "Quit", new ExitTask(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            if (task != null) {
                task.run();
            }
            ((MenuAnimation<Task<Void>>) menu).again();
        }
    }
}

