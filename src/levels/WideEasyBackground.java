package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class WideEasyBackground implements Sprite {

    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(239, 231, 176));
        for (int i = 5; i < 730; i = i + 10) {
            d.drawLine(200, 150, i, 250);
       }
       d.fillCircle(200, 150, 60);
       d.setColor(new Color(236, 215, 73));
       d.fillCircle(200, 150, 50);
       d.setColor(new Color(255, 225, 24));
       d.fillCircle(200, 150, 40);
    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }
}
