package game;

import basics.Ball;
import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class PrintingHitListener implements HitListener {
   /**
    * {@inheritDoc}
    */
    public void hitEvent(Block beingHit, Ball hitter) {
      System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
   }
}