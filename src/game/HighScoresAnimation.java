package game;

import java.awt.Image;

import javax.swing.ImageIcon;

import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable scores;

    /**
     * Constructor.
     * @param scores a HighScoresTable
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.stop = false;
        this.scores = scores;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
        try {
        //String backgroundPath = "background_images/wall.png";
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(backgroundPath);
        //BufferedImage img = ImageIO.read(is);
        ImageIcon icon = new ImageIcon("wall.png");
        Image img = icon.getImage();
        d.drawImage(0, 0, img);
        } catch (Exception e) {
            System.out.println(e);
        }
        d.setColor(java.awt.Color.BLACK);
        d.drawText(300, 100, "High Scores", 40);
        for (int i = 0; i < this.scores.getUsedCells(); i++) {
            d.drawText(250, 130 + (40 * (i + 1)), this.scores.getHighScores().get(i).getName(), 26);
            d.drawText(500, 130 + (40 * (i + 1)), String.valueOf(this.scores.getHighScores().get(i).getScore()), 26);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        return this.stop;
    }
}