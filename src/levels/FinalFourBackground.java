package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class FinalFourBackground implements Sprite {

    /**
     * {@inheritDoc}
     */
    public void drawOn(DrawSurface d) {
        // blue background
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, 800, 600);
       //left cloud
       for (int i = 146; i < 220; i = i + 8) {
           d.setColor(java.awt.Color.white);
           d.drawLine(i, 450, i - 35, 800);
       }
       d.setColor(new Color(204, 204, 204));
       d.fillCircle(150, 450, 20);
       d.setColor(new Color(204, 204, 204));
       d.fillCircle(160, 460, 22);
       d.setColor(new Color(187, 187, 187));
       d.fillCircle(175, 440, 24);
       d.setColor(new Color(170, 170, 170));
       d.fillCircle(210, 450, 27);
       d.setColor(new Color(170, 170, 170));
       d.fillCircle(187, 465, 17);
       //right cloud
       for (int i = 626; i < 700; i = i + 8) {
           d.setColor(java.awt.Color.white);
           d.drawLine(i, 550, i - 45, 800);
       }
       d.setColor(new Color(204, 204, 204));
       d.fillCircle(630, 520, 20);
       d.setColor(new Color(204, 204, 204));
       d.fillCircle(640, 555, 22);
       d.setColor(new Color(187, 187, 187));
       d.fillCircle(655, 520, 24);
       d.setColor(new Color(170, 170, 170));
       d.fillCircle(690, 530, 27);
       d.setColor(new Color(170, 170, 170));
       d.fillCircle(667, 540, 17);
    }

    /**
     * {@inheritDoc}
     */
    public void timePassed() {

    }

}
