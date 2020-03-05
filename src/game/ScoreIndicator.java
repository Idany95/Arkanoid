package game;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constuctor.
     * @param scoreCounter a Counter of score
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(81, 163, 255));
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(java.awt.Color.WHITE);
        d.drawText(360, 14, "Score: " + String.valueOf(this.scoreCounter.getValue()), 14);
    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }

}
