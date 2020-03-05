package game;
import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public interface Sprite {
    /**
    * draw the sprite to the screen.
    * @param d a DrawSurface
    */
    void drawOn(DrawSurface d);
   // notify the sprite that time has passed
    /**
    * let the Sprite know time have passed.
    */
    void timePassed();
}