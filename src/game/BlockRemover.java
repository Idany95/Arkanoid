package game;

import basics.Ball;
import basics.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count.
 * of the number of blocks that remain.
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class BlockRemover implements HitListener {
   private GameLevel game;
   private Counter remainingBlocks;

   /**
    * Constructor.
    * @param game a game
    * @param removedBlocks number of remaind blocks.
   */
   public BlockRemover(GameLevel game, Counter removedBlocks) {
       this.game = game;
       this.remainingBlocks = removedBlocks;
   }

   /**
    * {@inheritDoc}
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() == 0) {
           beingHit.removeFromGame(this.game);
           this.remainingBlocks.decrease(1);
           beingHit.removeHitListener(this);
       }
   }
}