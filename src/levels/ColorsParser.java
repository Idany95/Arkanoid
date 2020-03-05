package levels;

import java.awt.Color;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class ColorsParser {
    /**
     * parse color definition and return the specified color.
     * @param s string
     * @return a color
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.startsWith("color(RGB")) {
            String [] rgb = s.substring(10, s.length() - 2).split(",");
            int r = Integer.parseInt(rgb[0]);
            int g = Integer.parseInt(rgb[1]);
            int b = Integer.parseInt(rgb[2]);
            return new Color(r, g, b);
        }
        if (s.startsWith("color")) {
            s = s.substring(6, s.length() - 1);
            try {
              return (Color) Color.class.getField(s).get(null);
            } catch (Exception e) {
                System.err.println(e);
                return null;
            }
        }
        return null;
    }
 }