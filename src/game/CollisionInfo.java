package game;

import basics.Point;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class CollisionInfo {
    private Point collisionPoint = null;
    private Collidable collisionObject = null;

    /**
     * Return the point at which the collision occurs.
     * @return Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return collisionObject
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Method for setting collisionPoint field.
     * @param colPoint the Collision Point
     */
    public void setcollisionPoint(Point colPoint) {
        this.collisionPoint = colPoint;
    }

    /**
     * Method for setting collisionObject field.
     * @param colObject the Collidable object
     */
    public void setcollisionObject(Collidable colObject) {
        this.collisionObject = colObject;
    }
}