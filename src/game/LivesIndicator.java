package game;

import biuoop.DrawSurface;
/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    /**
     * Constructor.
     * @param livesCounter a counter of lives.
     */
    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }
    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.WHITE);
        d.drawText(130  , 14, "Lives: " + String.valueOf(this.livesCounter.getValue()), 14);
    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }
}