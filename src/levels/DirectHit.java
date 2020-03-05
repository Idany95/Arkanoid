package levels;

import java.util.ArrayList;
import java.util.List;

import basics.Block;
import basics.Velocity;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class DirectHit implements LevelInformation {

    /**
     * {@inheritDoc}
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(0.000001, 5);
        ballsVelocities.add(v);
        return ballsVelocities;
    }

    /**
     * {@inheritDoc}
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * {@inheritDoc}
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * {@inheritDoc}
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * {@inheritDoc}
     */
    public Sprite getBackground() {
        Sprite levelBackground = new DirectHitBackground();
        return levelBackground;
    }

    /**
     * {@inheritDoc}
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Block block1 = new Block(385, 170, 28, 28);
        block1.setColor(java.awt.Color.RED);
        blocks.add(block1);
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
