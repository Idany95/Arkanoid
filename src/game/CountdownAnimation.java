package game;

import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private double originalCountFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();
    private int frames;

    /**
     * Constructor.
     * @param numOfSeconds number of seconds on screen
     * @param countFrom countdown will start from...
     * @param gameScreen our screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.originalCountFrom = ((double) numOfSeconds / (double) countFrom) * 60;
        this.gameScreen = gameScreen;
        frames = 0;
        this.stop = false;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
        if (++frames > originalCountFrom) {
            countFrom--;
            frames = 0;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(java.awt.Color.gray);
        if (countFrom != 0) {
            d.drawText(385, d.getHeight() / 2, String.valueOf(this.countFrom), 80);
        } else {
            d.drawText(385, d.getHeight() / 2, "1", 80);
        }
        //this.sleeper.sleepFor((long) (this.numOfSeconds / this.countFrom) * 1000);
        if (this.countFrom == 0) {
            this.stop = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        return this.stop;
    }
}