package game;

import biuoop.DrawSurface;
/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;
    /**
     * Constructor.
     * @param levelName level's name, a String
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }
    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.WHITE);
        d.drawText(530  , 14, "Level Name: " + this.levelName, 14);
    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }
}