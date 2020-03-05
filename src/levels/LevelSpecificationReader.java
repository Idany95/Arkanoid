package levels;
import java.util.List;

import javax.imageio.ImageIO;

import basics.Block;
import basics.Velocity;
import biuoop.DrawSurface;
import game.Sprite;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class LevelSpecificationReader {
    private List<LevelInformation> levelList = new ArrayList<>();

    /**
     * read a level spesification file.
     * @param reader a reader
     * @return List of Level Information
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        String levelName = "default name";
        String ballVelocities = null;
        String background = null;
        int paddleSpeed = 5;
        int paddleWidth = 100;
        BlocksFromSymbolsFactory blocksFactory = null;
        List<Block> blocks = new ArrayList<Block>();
        int blocksStartX = 30;
        int blocksStartY = 30;
        int rowHeight = 20;
        int blocksNumber = 0;
        int i = 0;
        int xpos = 0;
        int ypos = 0;
        InputStream is = null;
        try {
            //reading level information
            while ((line = bufferedReader.readLine()) != null) {
                //if (line.equals("START_LEVEL"))
                if (line.startsWith("#")) {
                    continue;
                }
                if (line.startsWith("level_name:")) {
                    levelName = line.substring(11);
                    continue;
                }
                if (line.startsWith("ball_velocities:")) {
                    ballVelocities = line.substring(16);
                    continue;
                }
                if (line.startsWith("background:")) {
                    background = line.substring(11);
                    continue;
                }
                if (line.startsWith("paddle_speed:")) {
                    paddleSpeed = Integer.parseInt(line.substring(13));
                    continue;
                }
                if (line.startsWith("paddle_width:")) {
                    paddleWidth = Integer.parseInt(line.substring(13));
                    continue;
                }
                if (line.startsWith("block_definitions:")) {
                    String path = line.substring(18);
                    is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                    InputStreamReader r = new InputStreamReader(is);
                    BufferedReader bf = new BufferedReader(r);
                    blocksFactory = BlocksDefinitionReader.fromReader(bf);
                    continue;
                }
                if (line.startsWith("blocks_start_x:")) {
                    blocksStartX = Integer.parseInt(line.substring(15));
                    xpos = blocksStartX;
                    continue;
                }
                if (line.startsWith("blocks_start_y:")) {
                    blocksStartY = Integer.parseInt(line.substring(15));
                    ypos = blocksStartY;
                    continue;
                }
                if (line.startsWith("row_height:")) {
                    rowHeight = Integer.parseInt(line.substring(11));
                    continue;
                }
                if (line.startsWith("num_blocks:")) {
                    blocksNumber = Integer.parseInt(line.substring(11));
                    continue;
                }
                //reading blocks layout
                if (line.equals("START_BLOCKS")) {
                    while (!line.equals("END_BLOCKS")) {
                        for (int j = 0; j < line.length(); j++) {
                            String currentSymbol = String.valueOf(line.charAt(j));
                            if (blocksFactory.isSpaceSymbol(currentSymbol)) {
                                xpos = xpos + blocksFactory.getSpaceWidth(currentSymbol);
                                continue;
                            }
                            if (blocksFactory.isBlockSymbol(currentSymbol)) {
                                blocks.add(blocksFactory.getBlock(currentSymbol, xpos, ypos));
                                xpos = xpos + blocks.get(blocks.size() - 1).getWidth();
                            }
                        }
                        ypos = ypos + rowHeight;
                        xpos = blocksStartX;
                        line = bufferedReader.readLine();
                    }
                }
                //submitting level to our level list
                if (line.equals("END_LEVEL")) {
                    importToLevelInformation(levelName, ballVelocities, background,  paddleSpeed,  paddleWidth,
                                        blocksNumber, blocks);
                    //reset variables for new level
                    blocksFactory = null;
                    blocks = new ArrayList<Block>();
                    blocksStartX = 30;
                    blocksStartY = 30;
                    rowHeight = 20;
                    blocksNumber = 0;
                    i = 0;
                    xpos = 0;
                    ypos = 0;
                    levelName = "default name";
                    ballVelocities = null;
                    background = null;
                    paddleSpeed = 5;
                    paddleWidth = 100;
                    continue;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return this.levelList;
    }

    /**
     * Import all information read to a levelInformation object.
     * @param levelName name
     * @param ballVelocities balls velocities
     * @param background background
     * @param paddleSpeed paddle speed
     * @param paddleWidth paddle width
     * @param blocksNumber number of blocks
     * @param blocks actual blocks
     */
    public void importToLevelInformation(String levelName, String ballVelocities, String background,
            int paddleSpeed, int paddleWidth, int blocksNumber, List<Block> blocks) {
        this.levelList.add(new LevelInformation() {
            @Override
            public int paddleWidth() {
                return paddleWidth;
            }

            @Override
            public int paddleSpeed() {
                return paddleSpeed;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return blocksNumber;
            }

            @Override
            public int numberOfBalls() {
                return initialBallVelocities().size();

            }

            @Override
            public String levelName() {
                return levelName;
            }

            @Override
            public List<Velocity> initialBallVelocities() {
                List<Velocity> bVelocities = new ArrayList<Velocity>();
                String [] parts = ballVelocities.split(" ");
                double angle;
                double speed;
                for (int i = 0; i < parts.length; i++) {
                    String[] partsOfparts = parts[i].split(",");
                    angle = Integer.parseInt(partsOfparts[0]);
                    speed = Integer.parseInt(partsOfparts[1]);
                    if (angle == 0) {
                        angle = 0.00001;
                    }
                    bVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
                }
                return bVelocities;
            }

            @Override
            public Sprite getBackground() {
                return new Sprite() {

                    @Override
                    public void timePassed() {

                    }

                    @Override
                    public void drawOn(DrawSurface d) {
                        try {
                            if (background.contains("color")) {
                                d.setColor(ColorsParser.colorFromString(background));
                                d.fillRectangle(0, 0, 800, 600);
                            }
                            if (background.contains("image")) {
                                String backgroundPath = background.substring(6, background.length() - 1);
                                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(backgroundPath);
                                BufferedImage img = ImageIO.read(is);
                                d.drawImage(0, 0, img);
                            }
                            //**//
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                };
            }

            @Override
            public List<Block> blocks() {
                return blocks;
            }
        });
    }
}

