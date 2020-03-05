package levels;

import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos x position
     * @param ypos y position
     * @return a Block
     */
    Block create(int xpos, int ypos);

    /**
     * set stroke.
     * @param stroke a stroke
     */
    void setStroke(java.awt.Color stroke);

    /**
     * set fillers.
     * @param fillersArray fillers
     */
    void setFillers(Object [] fillersArray);
 }