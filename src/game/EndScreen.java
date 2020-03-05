package game;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class EndScreen implements Animation {
    private boolean stop;
    private boolean win;
    private int score;
    private GUI gui;

    /**
     * Constructor.
     * @param score a score
     * @param win boolean variable, true or false
     * @param gui our gui
     */
    public EndScreen(int score, boolean win, GUI gui) {
       this.score = score;
       this.win = win;
       this.stop = false;
       this.gui = gui;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
       if (win) {
           d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
       } else {
           d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
       }
       //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
       //   this.gui.close();;
       //}
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        return this.stop;
    }
 }