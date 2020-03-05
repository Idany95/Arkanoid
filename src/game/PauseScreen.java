package game;

import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
       this.stop = false;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
       d.drawText(175, d.getHeight() / 2, "paused -- press space to continue", 32);
       //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        return this.stop;
    }
 }