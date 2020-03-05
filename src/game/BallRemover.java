package game;

import basics.Ball;
import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class BallRemover implements HitListener {
   private GameLevel game;
   private Counter remainingBalls;

   /**
    * Constructor.
    * @param game a game
    * @param remainingBalls Counter of remaining balls
    */
   public BallRemover(GameLevel game, Counter remainingBalls) {
       this.game = game;
       this.remainingBalls = remainingBalls;
   }

    /**
     * {@inheritDoc}
     */
   public void hitEvent(Block beingHit, Ball hitter) {
           hitter.removeFromGame(this.game);
           this.remainingBalls.decrease(1);
       }
}