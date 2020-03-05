package levels;

import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class DataToBlockCreator implements BlockCreator {
    //default parameters
    private int defaultHeight;
    private int defaultWidth;
    //Specific parameters
    private int height = 0;
    private int width = 0;
    private int hitPoints = 1;
    private Object [] fillers = null;
    private java.awt.Color stroke = null;

    /**
     * Constructor.
     * @param height int
     * @param width int
     * @param hitPoints int
     * @param defaultHeight int
     * @param defaultWidth int
     */
    public DataToBlockCreator(int height, int width, int hitPoints,
                            int defaultHeight, int defaultWidth) {
        this.defaultHeight = defaultHeight;
        this.defaultWidth = defaultWidth;
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
    }

    /**
     * {@inheritDoc}
     */
    public void setStroke(java.awt.Color newStroke) {
        this.stroke = newStroke;
    }

    /**
     * {@inheritDoc}
     */
    public void setFillers(Object [] fillersArray) {
        this.fillers = fillersArray;
    }
    /**
     * {@inheritDoc}
     */
    public Block create(int xpos, int ypos) {
        Block b = new Block(xpos, ypos, this.height, this.width,
                            this.hitPoints, this.defaultHeight, this.defaultWidth);
        b.setFillers(this.fillers);
        b.setStroke(this.stroke);
        return b;
    }
}
