package levels;

import java.util.LinkedHashMap;
import java.util.Map;

import basics.Block;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new LinkedHashMap<String, Integer>();
    private Map<String, BlockCreator> blockCreators = new LinkedHashMap<String, BlockCreator>();

    /**
     * returns true if 's' is a valid space symbol.
     * @param s symbol
     * @return true or false
     */
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * returns true if 's' is a valid block symbol.
     * @param s symbol
     * @return true or false
     */
    public boolean isBlockSymbol(String s) {
        if (blockCreators.containsKey(s)) {
            return true;
        }
        return false;

    }

    /**
     * Return a block according to the definitions associated.
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s a symbol
     * @param xpos x postion
     * @param ypos y position
     * @return a block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s string of a spaceWidth
     * @return a spaceWidth
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * @param s string, symbol
     * @param num num, width
     */
    public void addSpaceWidth(String s, int num) {
        spacerWidths.put(s, num);
    }

    /**
     * add blockCreator to map.
     * @param s a string, symbol
     * @param bc a blockCreator
     */
    public void addBlockCreator(String s, BlockCreator bc) {
        blockCreators.put(s, bc);
    }
 }