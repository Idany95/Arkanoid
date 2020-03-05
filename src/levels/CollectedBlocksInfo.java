package levels;

import java.util.List;

import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class CollectedBlocksInfo {
    private int blocksStartX;
    private int blocksStartY;
    private int blocksNumber;
    private List<Block> blocks;

    /**
     * Constructor.
     * @param blocksStartX int
     * @param blocksStartY int
     * @param blocksNumber int
     * @param blocks List
     */
    CollectedBlocksInfo(int blocksStartX, int blocksStartY, int blocksNumber, List<Block> blocks) {
        this.blocksStartX = blocksStartX;
        this.blocksStartY = blocksStartY;
        this.blocksNumber = blocksNumber;
        this.blocks = blocks;
    }

    /**
     * getter.
     * @return int
     */
    public int blockStartX() {
        return this.blocksStartX;
    }

    /**
     * getter.
     * @return int
     */
    public int blockStartY() {
        return this.blocksStartY;
    }

    /**
     * getter.
     * @return int
     */
    public int blocksNumber() {
        return this.blocksNumber;
    }

    /**
     * getter.
     * @return list
     */
    public List<Block> blocks() {
        return this.blocks;
    }
}
