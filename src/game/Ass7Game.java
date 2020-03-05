package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Ass7Game {
    /**
     * Class' main function.
     * @param args string[] args - main default
     * @throws IOException IO exception
     */
    public static void main(String [] args) throws IOException {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        biuoop.KeyboardSensor ks = gui.getKeyboardSensor();
        biuoop.Sleeper sleeper = new Sleeper();
        File f = new File("highscores.ser");
        HighScoresTable table = HighScoresTable.loadFromFile(f);
        while (true) {
            GameFlow gameFlow = new GameFlow(ar, ks, sleeper, gui, table);
            Menu<Task<Void>> menu = new MenuAnimation("Main Menu", ks);
            menu.addSelection("s", "Start game", new Task<Void>() {
                public Void run() {
                    Menu<Task<Void>> difficultyMenu = new MenuAnimation("Difficulty Menu", ks);
                    LevelSetsReader levelSetsReader = new LevelSetsReader();
                    InputStream is = null;
                    if (args.length > 0) {
                         is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
                    } else {
                         is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
                    }
                    try {
                        InputStreamReader r = new InputStreamReader(is);
                        BufferedReader bf = new BufferedReader(r);
                        List<Selection> selections = levelSetsReader.fromReader(bf, gameFlow);
                        for (Selection s : selections) {
                            difficultyMenu.addSelection(s);
                        }
                            ar.run(difficultyMenu);
                            // wait for user selection
                            Task<Void> task = (Task<Void>) difficultyMenu.getStatus();
                            if (task != null) {
                                task.run();
                            }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    return null;
                }
            });
            menu.addSelection("h", "High scores", new Task<Void>() {
                public Void run() {
                    ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                            new HighScoresAnimation(table)));
                    return null;
                }
            });
            menu.addSelection("q", "Quit game", new Task<Void>() {
                public Void run() {
                    gui.close();
                    System.exit(1);
                    return null;
                }
            });
            ar.run(menu);
            // wait for user selection
            Task<Void> task = (Task<Void>) menu.getStatus();
            if (task != null) {
            task.run();
            }
        }
    }
}