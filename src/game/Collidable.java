package game;

import basics.Ball;
import basics.Point;
import basics.Rectangle;
import basics.Velocity;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public interface Collidable {
  /**
   * Return the "collision shape" of the object.
   * @return Rectangle Block's Rectangle
   */
   Rectangle getCollisionRectangle();

   /**
   * Notify the object that we collided with it at collisionPoint with a given velocity.
   * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
   * @param hitter hitting ball
   * @param collisionPoint collision Point
   * @param currentVelocity Ball's current Velocity
   * @return Velocity Ball's new Velocity
   */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
