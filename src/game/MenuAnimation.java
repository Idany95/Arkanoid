package game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-06-01
 * @param <T> an object
 */
public class MenuAnimation<T> implements Menu<T> {
    private String name;
    private List<Selection> selections = new ArrayList<Selection>();
    private boolean stop;
    private KeyboardSensor ks;

    /**
     * Constructor.
     * @param name menu name
     * @param ks keyboard sensor
     */
    public MenuAnimation(String name, KeyboardSensor ks) {
        this.name = name;
        this.stop = false;
        this.ks = ks;
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
        d.drawText(300, 200, this.name, 40);
        for (int i = 0; i < this.selections.size(); i++) {
            d.drawText(300, 200 + 60 * (i + 1), "(" + this.selections.get(i).getKey() + ")", 26);
            d.drawText(350, 200 + 60 * (i + 1), this.selections.get(i).getMessage(), 26);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        if (this.getStatus() != null) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selection(key, message, returnVal));
    }

    /**
     * {@inheritDoc}
     */
    public void addSelection(Selection s) {
        this.selections.add(s);
    }

    /**
     * {@inheritDoc}
     */
    public T getStatus() {
        for (int i = 0; i < this.selections.size(); i++) {
            if (this.ks.isPressed(this.selections.get(i).getKey())) {
                return (T) this.selections.get(i).getReturnVal();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.selections.add(new Selection(key, message, subMenu));
    }

}
