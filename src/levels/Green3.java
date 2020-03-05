package levels;

import java.awt.Color;
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
public class Green3 implements LevelInformation {

    /**
     * {@inheritDoc}
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(30, 5);
        ballsVelocities.add(v);
        v = Velocity.fromAngleAndSpeed(330, 5);
        ballsVelocities.add(v);
        return ballsVelocities;
    }

    /**
     * {@inheritDoc}
     */
    public int paddleSpeed() {
        return 5;
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
        return "Green 3";
    }

    /**
     * {@inheritDoc}
     */
    public Sprite getBackground() {
        Sprite levelBackground = new Green3Background();
        return levelBackground;
    }

    /**
     * {@inheritDoc}
     */
    public List<Block> blocks() {
        Color [] colorArr = {new Color(128, 128, 128), new Color(255, 0, 0), new Color(255, 255, 0),
                new Color(0, 0, 255), new Color(255, 255, 255)};
        List<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i <= 4; i++) {
            for (int j = 270 + (i * 50); j < 770; j = j + 50) {
                Block tempBlock = new Block(j, 200 + (i * 20), 50, 20);
                tempBlock.setColor(colorArr[i]);
                blocks.add(tempBlock);
            }
        }
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
