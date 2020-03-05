package game;

import java.util.ArrayList;
import java.util.List;

import basics.Line;
import basics.Point;
import basics.Rectangle;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * add the given collidable to the environment.
     * @param c a Collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory Ball's trajectory
     * @return CollisionInfo object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
       CollisionInfo colInfo = new CollisionInfo();
       Rectangle rec;
       double closestCollisionDistance = 0;
       Point closestCollisionPoint = null, currentPoint = null;
       Collidable colObject = null;
       for (Collidable c : collidables) {
           rec = c.getCollisionRectangle();
           if (!(rec.intersectionPoints(trajectory).isEmpty())) {
               currentPoint = trajectory.closestIntersectionToStartOfLine(rec);
               if (closestCollisionPoint == null) {
                   closestCollisionPoint = currentPoint;
                   closestCollisionDistance = closestCollisionPoint.distance(trajectory.start());
                   colObject = c;
               } else if (closestCollisionDistance > currentPoint.distance(trajectory.start())) {
                   closestCollisionPoint = currentPoint;
                   closestCollisionDistance = currentPoint.distance(trajectory.start());
                   colObject = c;
               }
           }
       }
       if (closestCollisionPoint == null || colObject == null) {
           return null;
       }
       colInfo.setcollisionPoint(closestCollisionPoint);
       colInfo.setcollisionObject(colObject);
       return colInfo;
   }

   /**
    * Access method to collidables field.
    * @return collidables (List<Collidable>)
    */
   public List<Collidable> getCollidables() {
       return this.collidables;
   }
}