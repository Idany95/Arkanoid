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
public class WideEasy implements LevelInformation {

    /**
     * {@inheritDoc}
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * {@inheritDoc}
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();
        for (int i = 0; i <= 4; i++) {
        Velocity v = Velocity.fromAngleAndSpeed(50 - (i * 10), 5);
        ballsVelocities.add(v);
        }
        for (int i = 0; i <= 4; i++) {
        Velocity v = Velocity.fromAngleAndSpeed(310 + (i * 10), 5);
        ballsVelocities.add(v);
        }
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
        return 600;
    }

    /**
     * {@inheritDoc}
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * {@inheritDoc}
     */
    public Sprite getBackground() {
        Sprite levelBackground = new WideEasyBackground();
        return levelBackground;
    }

    /**
     * {@inheritDoc}
     */
    public List<Block> blocks() {
        Color [] colorArr = {new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 200, 0),
                new Color(255, 200, 0), new Color(255, 255, 0), new Color(255, 255, 0),
                new Color(0, 255, 0), new Color(0, 255, 0), new Color(0, 255, 0), new Color(0, 0, 255),
                new Color(0, 0, 255), new Color(255, 175, 175), new Color(255, 175, 175),
                new Color(0, 255, 255), new Color(0, 255, 255)};
        List<Block> blocks = new ArrayList<Block>();
        Block tempBlock;
        int j = 0;
        for (int i = 30; i < 740; i = i + 49) {
            if (j != 14) {
                tempBlock = new Block(i, 250, 49, 25);
            } else {
                tempBlock = new Block(i, 250, 54, 25);

            }
            tempBlock.setColor(colorArr[j]);
            j++;
            blocks.add(tempBlock);
        }
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
