package levels;

import java.io.BufferedReader;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class BlocksDefinitionReader {

    /**
     * read blocks definition file.
     * @param reader a reader
     * @return BlockFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory blocksFactory = new BlocksFromSymbolsFactory();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        String symbol = null;
        int defaultHeight = 20;
        int defaultWidth = 50;
        int defaultHitPoints = 1;
        java.awt.Color defaultStroke = null;
        int height = 0;
        int width = 0;
        int hitPoints = 0;
        Object [] fillersArray = new Object [100];
        Object [] defaultFillersArray = new Object [100];
        java.awt.Color stroke = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("default")) {
                    String [] blockParts = line.split(" ");
                    for (int i = 0; i < blockParts.length; i++) {
                        if (blockParts[i].startsWith("height:")) {
                            defaultHeight = Integer.parseInt(blockParts[i].substring(7));
                        }
                        if (blockParts[i].startsWith("width:")) {
                            defaultWidth = Integer.parseInt(blockParts[i].substring(6));
                        }
                        if (blockParts[i].startsWith("hit_points:")) {
                            defaultHitPoints = Integer.parseInt(blockParts[i].substring(11));
                        }
                        if (blockParts[i].startsWith("fill")) {
                            defaultFillersArray = (Object[]) BlockFillParser.parseFill(blockParts[i],
                                                                                defaultFillersArray);
                        }
                        if (blockParts[i].startsWith("stroke:")) {
                            String strokeString = blockParts[i].substring(7);
                                defaultStroke = ColorsParser.colorFromString(strokeString);
                        }
                    }
                }
                if (line.startsWith("bdef")) {
                    String [] blockParts = line.split(" ");
                    for (int i = 0; i < blockParts.length; i++) {
                        if (blockParts[i].startsWith("symbol:")) {
                            symbol = blockParts[i].substring(7);
                        }
                        if (blockParts[i].startsWith("height:")) {
                            height = Integer.parseInt(blockParts[i].substring(7));
                        }
                        if (blockParts[i].startsWith("width:")) {
                            width = Integer.parseInt(blockParts[i].substring(6));
                        }
                        if (blockParts[i].startsWith("hit_points:")) {
                            hitPoints = Integer.parseInt(blockParts[i].substring(11));
                        }
                        if (blockParts[i].startsWith("fill")) {
                            fillersArray = (Object[]) BlockFillParser.parseFill(blockParts[i], fillersArray);
                        }
                        if (blockParts[i].startsWith("stroke:")) {
                            String strokeString = blockParts[i].substring(7);
                                stroke = ColorsParser.colorFromString(strokeString);
                        }
                    }
                    //stroke
                    if (stroke == null) {
                        stroke = defaultStroke;
                    }
                    //hit Points
                    if (hitPoints == 0) {
                        hitPoints = defaultHitPoints;
                    }
                    //fillers
                    for (int i = 1; i <= hitPoints; i++) {
                        if (fillersArray[i] == null) {
                            fillersArray[i] = defaultFillersArray[i];
                        }
                    }
                    BlockCreator bc = new DataToBlockCreator(height, width, hitPoints,
                            defaultHeight, defaultWidth);
                    bc.setFillers(fillersArray);
                    bc.setStroke(stroke);
                    blocksFactory.addBlockCreator(symbol, bc);
                    continue;
                }
                if (line.startsWith("sdef")) {
                    String [] blockParts = line.split(" ");
                    for (int i = 0; i < blockParts.length; i++) {
                        if (blockParts[i].startsWith("symbol:")) {
                            symbol = blockParts[i].substring(7);
                        }
                        if (blockParts[i].startsWith("width:")) {
                            width = Integer.parseInt(blockParts[i].substring(6));
                        }
                    }
                    blocksFactory.addSpaceWidth(symbol, width);
                }
            }
            bufferedReader.close();
            return blocksFactory;
        } catch (Exception e) {
           System.err.println(e);
           return null;
        }
    }
 }