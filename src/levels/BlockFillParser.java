package levels;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class BlockFillParser {
    /**
     * Parsing string to fill.
     * @param s our string
     * @param fillers our array
     * @return our array
     */
    public static Object parseFill(String s, Object[] fillers) {
        try {
        if (s.startsWith("fill:")) {
            String fillString = s.substring(5);
            if (fillString.contains("color")) {
                fillers[1] = ColorsParser.colorFromString(fillString);
            }
            if (fillString.contains("image")) {
                String path = fillString.substring(6, fillString.length() - 1);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                BufferedImage img = ImageIO.read(is);
                fillers[1] = img;
            }
        }
        if (s.startsWith("fill-")) {
            int index = 0;
            int j = 0;
            while (!(s.charAt(5 + j) == ':')) {
                j++;
            }
            index = Integer.parseInt(s.substring(5, 5 + j));
            String fillString = s.substring(5 + j + 1);
            if (fillString.contains("color")) {
                fillers[index] = ColorsParser.colorFromString(fillString);
            }
            if (fillString.contains("image")) {
                String path = fillString.substring(6, fillString.length() - 1);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                BufferedImage img = ImageIO.read(is);
                fillers[index] = img;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fillers;
    }
 }
