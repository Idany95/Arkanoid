package game;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond = 60;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * Constructor.
     * @param gui our gui
     * @param framesPerSecond FPS (60 by defult)
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Animation loop.
     * @param animation animation object
     */
    public void run(Animation animation) {
       int millisecondsPerFrame = 1000 / this.framesPerSecond;
       while (!animation.shouldStop()) {
          long startTime = System.currentTimeMillis(); // timing
          DrawSurface d = gui.getDrawSurface();

          animation.doOneFrame(d);

          gui.show(d);
          long usedTime = System.currentTimeMillis() - startTime;
          long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
          if (milliSecondLeftToSleep > 0) {
              this.sleeper.sleepFor(milliSecondLeftToSleep);
          }
       }
    }
 }