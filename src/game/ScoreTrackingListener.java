package game;
import basics.Ball;
import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
class ScoreTrackingListener implements HitListener {
   private Counter currentScore;

 /**
  * Constructor.
 * @param scoreCounter a counter of score
 */
public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

/**
 * {@inheritDoc}
 */
public void hitEvent(Block beingHit, Ball hitter) {
      this.currentScore.increase(5);
      if (beingHit.getHitPoints() == 0) {
          this.currentScore.increase(10);
      }
   }
}