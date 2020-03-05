package game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private biuoop.Sleeper sleeper;
    private GUI gui;
    private Counter scoreCounter = new Counter(0);
    private Counter livesCounter = new Counter(7);
    private HighScoresTable table;

    /**
     * Constructor.
     * @param ar animation runner
     * @param ks keyboard sensor
     * @param sleeper sleeper
     * @param gui gui
     * @param table an HighScoresTable
     * @throws IOException IO Exception
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, biuoop.Sleeper sleeper,
                    GUI gui, HighScoresTable table) throws IOException {
        this.ar = ar;
        this.ks = ks;
        this.sleeper = sleeper;
        this.gui = gui;
        this.table = table;
    }

    /**
     * GameFlow's main method that's in charge of running GameLevels.
     * @param levels our levels list
     */
    public void runLevels(List<LevelInformation> levels) {

       for (LevelInformation levelInfo : levels) {
          boolean lastLevel = false;
          if (levelInfo == levels.get(levels.size() - 1)) {
              lastLevel = true;
          }
          GameLevel level = new GameLevel(levelInfo, this.ar, this.gui,
                              this.scoreCounter, this.livesCounter, lastLevel, this.table);
          level.setKS(this.ks);
          level.initialize();
          while (level.getBlockCounter().getValue() > 0 && level.getlivesCounter().getValue() > 0) {
             level.playOneTurn();
          }

          if (level.getlivesCounter().getValue() == 0) {
             break;
          }
       }
       File f = new File("Highscores.ser");
       try {
           this.table.save(f);
       } catch (Exception e) {
           System.err.println("Error while saving file");
       }
    }

    /**
     * HighScoresTable getter.
     * @return HighScoresTable object
     */
    public HighScoresTable getHighScoresTable() {
        return this.table;
    }
 }