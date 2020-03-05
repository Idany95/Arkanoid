package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class Green3Background implements Sprite {

    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
        // green background
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 0, 800, 600);
        //building
        d.setColor(new Color(46, 42, 41));
        d.fillRectangle(70, 420, 110, 600);
        // building top
        d.setColor(new Color(62, 58, 57));
        d.fillRectangle(110, 370, 30, 50);
        //building line
        d.setColor(new Color(78, 74, 73));
        d.fillRectangle(120, 220, 10, 150);
        //windows
        d.setColor(java.awt.Color.white);
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j <= 4; j++) {
                d.fillRectangle(60 + (i * 20), 430 + (j * 40), 10, 30);
            }
       }
       d.setColor(new Color(216, 172, 102));
       d.fillCircle(125, 220, 12);
       d.setColor(new Color(246, 77, 54));
       d.fillCircle(125, 220, 8);
       d.setColor(java.awt.Color.white);
       d.fillCircle(125, 220, 4);

    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }

}
