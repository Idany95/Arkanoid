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
public class FinalFour implements LevelInformation {

    /**
     * {@inheritDoc}
     */
    public int numberOfBalls() {
        return 3;
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
        v = Velocity.fromAngleAndSpeed(0.000001, 5);
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
        Sprite levelBackground = new FinalFourBackground();
        return levelBackground;
    }

    /**
     * {@inheritDoc}
     */
    public List<Block> blocks() {
        Color [] colorArr = {new Color(128, 128, 128), new Color(255, 0, 0), new Color(255, 255, 0),
                new Color(0, 255, 0), new Color(255, 255, 255), new Color(255, 175, 175),
                new Color(0, 255, 255)};
        List<Block> blocks = new ArrayList<Block>();
        Block tempBlock;
        for (int i = 0; i <= 6; i++) {
            for (int j = 30; j < 717; j = j + 49) {
                if (j != 716) {
                    tempBlock = new Block(j, 200 + (i * 20), 49, 20);
                } else {
                    tempBlock = new Block(j, 200 + (i * 20), 54, 20);
                }
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
        return 105;
    }
}
