package basics;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.Collidable;
import game.GameLevel;
import game.HitListener;
import game.HitNotifier;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private java.awt.Color color = java.awt.Color.GRAY;
    private int numberOfHits = 1;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Object [] fillers = new Object [100];
    private java.awt.Color stroke;

    /**
     * A Block object constructor.
     * @param rec Block's Rectangle
     */
    public Block(Rectangle rec) {
        this.rec = rec;
    }

    /**
     * A Block object constructor.
     * @param x Block's Rectangle's upperLeft x-Axis location
     * @param y Block's Rectangle's upperLeft y-Axis location
     * @param width Block's Rectangle's width
     * @param height Block's Rectangle's height
     */
    public Block(double x, double y, double width, double height) {
        Point upperLeft = new Point(x, y);
        this.rec = new Rectangle(upperLeft, width, height);
    }

    /**
     * Constructor.
     * @param x double
     * @param y double
     * @param width double
     * @param height double
     * @param hitPoints int
     * @param defaultHeight int
     * @param defaultWidth int
     */
    public Block(double x, double y, double width, double height, int hitPoints,
                int defaultHeight, int defaultWidth) {
        Point upperLeft = new Point(x, y);
        //height
        if (height == 0) {
            height = defaultHeight;
        }
        //width
        if (width == 0) {
            width = defaultWidth;
        }
        //create rectangle
        this.rec = new Rectangle(upperLeft, width, height);
        //number of hits
        this.setNumberOfHits(hitPoints);
    }

    /**
     * set stroke.
     * @param newStroke a stroke
     */
    public void setStroke(java.awt.Color newStroke) {
        this.stroke = newStroke;
    }

    /**
     * set fillers.
     * @param fillersArray fillers
     */
    public void setFillers(Object [] fillersArray) {
        this.fillers = fillersArray;
    }
    /**
     * Return the "collision shape" of the object.
     * @return Rectangle Block's Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter hitting ball
     * @param collisionPoint collision Point
     * @param currentVelocity Ball's current Velocity
     * @return Velocity Ball's new Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle blockRec = this.getCollisionRectangle();
        Point topLeftVertex = blockRec.getUpperLeft();
        Point topRightVertex = new Point(blockRec.getUpperLeft().getX() + blockRec.getWidth(),
                                            blockRec.getUpperLeft().getY());
        Point bottomRightVertex = new Point(blockRec.getUpperLeft().getX() + blockRec.getWidth(),
                                            blockRec.getUpperLeft().getY() + blockRec.getHeight());
        Point bottomLeftVertex = new Point(blockRec.getUpperLeft().getX(),
                                            blockRec.getUpperLeft().getY() + blockRec.getHeight());
        //notify listeners
        //decrease one hit
        if (this.numberOfHits > 0) {
            this.numberOfHits = this.numberOfHits - 1;
        }
        this.notifyHit(hitter);
        if (collisionPoint.distance(topLeftVertex) < 0.2
                || collisionPoint.distance(topRightVertex) < 0.2
                || collisionPoint.distance(bottomRightVertex) < 0.2
                || collisionPoint.distance(bottomLeftVertex) < 0.2) {
            return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY());
        } else {
                //left Line hit
                if (collisionPoint.getX() == blockRec.getUpperLeft().getX()) {
                    currentVelocity.setDX(-currentVelocity.getDX());
                }
                //top Line hit
                if (collisionPoint.getY() == blockRec.getUpperLeft().getY()) {
                    currentVelocity.setDY(-currentVelocity.getDY());
                }
                //right Line hit
                if (collisionPoint.getX() == (blockRec.getUpperLeft().getX() + blockRec.getWidth())) {
                    currentVelocity.setDX(-currentVelocity.getDX());
                }
                //bottom Line hit
                if (collisionPoint.getY() == (blockRec.getUpperLeft().getY() + blockRec.getHeight())) {
                    currentVelocity.setDY(-currentVelocity.getDY());
                }
                return currentVelocity;
        }
    }

    /**
     * Method for drawing a Ball on a given DrawSurface.
     * @param surface current DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        if (this.fillers[this.getHitPoints()] instanceof java.awt.Color) {
            surface.setColor((Color) this.fillers[this.getHitPoints()]);
            surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                                  (int) this.getCollisionRectangle().getUpperLeft().getY(),
                                  (int) this.getCollisionRectangle().getWidth(),
                                  (int) this.getCollisionRectangle().getHeight());
        } else if (this.fillers[this.getHitPoints()] instanceof Image) {
           surface.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX(),
                               (int) this.getCollisionRectangle().getUpperLeft().getY(),
                               (Image) this.fillers[this.getHitPoints()]);
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                                  (int) this.getCollisionRectangle().getUpperLeft().getY(),
                                  (int) this.getCollisionRectangle().getWidth(),
                                  (int) this.getCollisionRectangle().getHeight());
        }
        if (stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                                  (int) this.getCollisionRectangle().getUpperLeft().getY(),
                                  (int) this.getCollisionRectangle().getWidth(),
                                  (int) this.getCollisionRectangle().getHeight());
        }
        /*
        double textX = this.getCollisionRectangle().getUpperLeft().getX()
                        + (this.getCollisionRectangle().getWidth() / 2) - 3;
        double textY = this.getCollisionRectangle().getUpperLeft().getY()
                        + (this.getCollisionRectangle().getHeight() / 2) + 5;
        String textString;
        if (this.numberOfHits > 0) {
            textString = String.valueOf(this.numberOfHits);
        } else {
            textString = "X";
        }
        surface.setColor(java.awt.Color.WHITE);
        surface.drawText((int) textX, (int) textY, textString, 14);*/
    }

    /**
     * Method for setting Block's fill color.
     * @param newColor Block's new fill color
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * Method for adding the Block to a Game object.
     * @param g Game object
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Method for calling Block's moveOneStep method when time is passed.
     */
    public void timePassed() {

    }

    /**
     * Method for setting Block's number of hits.
     * @param hitsNum number of hits
     */
    public void setNumberOfHits(int hitsNum) {
        this.numberOfHits = hitsNum;
    }

    /**
     * remove current block from game.
     * @param game a game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * {@inheritDoc}
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * {@inheritDoc}
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    /**
     * notify listeners there's a hit.
     * @param hitter a ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
     }

    /**
     * hit points getter.
     * @return int number of hits
     */
    public int getHitPoints() {
        return this.numberOfHits;
    }

    /**
     * get Block's width.
     * @return int
     */
    public int getWidth() {
        return (int) this.getCollisionRectangle().getWidth();
    }
}