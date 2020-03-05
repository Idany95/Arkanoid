package levels;

import java.util.List;

import basics.Block;
import basics.Velocity;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public interface LevelInformation {

    /**
     * Get the number of Balls.
     * @return int number of Balls;
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Get paddleSpeed.
     * @return speed
     */
    int paddleSpeed();

    /**
     * get PaddleWidth.
     * @return paddle's width
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     * @return a name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return a sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return number of blocks
     */
    int numberOfBlocksToRemove();
 }