package game;

import basics.Ball;
import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit hitted block
     * @param hitter hitting ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}