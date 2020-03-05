package game;

import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public interface Animation {

    /**
     * what will happen each frame.
     * @param d things will be drawn to d (DrawSurface)
     */
    void doOneFrame(DrawSurface d);

    /**
     * Tells if the animation should stop.
     * @return true or false (stop field)
     */
    boolean shouldStop();
 }