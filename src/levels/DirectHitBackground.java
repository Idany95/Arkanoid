package levels;

import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class DirectHitBackground implements Sprite {

    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
       d.setColor(java.awt.Color.BLACK);
       d.fillRectangle(0, 0, 800 , 600);
       d.setColor(java.awt.Color.BLUE);
       d.drawLine(400, 330, 400, 30);
       d.drawLine(250, 184, 550, 184);
       d.drawCircle(400, 184, 100);
       d.drawCircle(400, 184, 80);
       d.drawCircle(400, 184, 60);
    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }

}
