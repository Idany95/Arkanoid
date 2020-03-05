package game;

import basics.Ball;
import basics.Point;
import basics.Rectangle;
import basics.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Paddle implements Sprite, Collidable {
   private Rectangle rec;
   private biuoop.KeyboardSensor keyboard;
   private int speed = 5;

   /**
    * Paddle constructor.
    * @param rec Paddle's Rectangle
    * @param keyboard keyboardSensor
    */
   public Paddle(Rectangle rec, biuoop.KeyboardSensor keyboard) {
       this.rec = rec;
       this.keyboard = keyboard;
   }

   /**
    * Paddle constructor.
    * @param rec Paddle's Rectangle
    * @param keyboard keyboardSensor
    * @param speed paddle's speed
    */
   public Paddle(Rectangle rec, biuoop.KeyboardSensor keyboard, int speed) {
       this.rec = rec;
       this.keyboard = keyboard;
       this.speed = speed;
   }

   /**
    * move Paddle to the left.
    */
   public void moveLeft() {
       if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
               && this.getCollisionRectangle().getUpperLeft().getX() >= (30 + this.speed)) {
           this.rec.setUpperLeftX(this.rec.getUpperLeft().getX() - this.speed);
       }
   }
   /**
    * move Paddle to the right.
    */
   public void moveRight() {
       if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
               && this.getCollisionRectangle().getUpperLeft().getX() <= (770 - this.rec.getWidth() - this.speed)) {
           this.rec.setUpperLeftX(this.rec.getUpperLeft().getX() + this.speed);
       }
   }

   // Sprite
   /**
    * Method for calling Block's moveOneStep method when time is passed.
    */
   public void timePassed() {
       moveLeft();
       moveRight();
   }
   /**
    * Method for drawing a Ball on a given DrawSurface.
    * @param d current DrawSurface
    */
   public void drawOn(DrawSurface d) {
       d.setColor(java.awt.Color.ORANGE);
       d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                       (int) this.getCollisionRectangle().getUpperLeft().getY(),
                       (int) this.getCollisionRectangle().getWidth(),
                       (int) this.getCollisionRectangle().getHeight());
       d.setColor(java.awt.Color.BLACK);
       d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                       (int) this.getCollisionRectangle().getUpperLeft().getY(),
                       (int) this.getCollisionRectangle().getWidth(),
                       (int) this.getCollisionRectangle().getHeight());

   }

   // Collidable
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
       double paddleWidth = this.getCollisionRectangle().getWidth();
       double x = this.getCollisionRectangle().getUpperLeft().getX();
       double sectionLength = paddleWidth / 5;
       double sectionNo0 = x;
       double sectionNo1 = sectionNo0 + sectionLength;
       double sectionNo2 = sectionNo1 + sectionLength;
       double sectionNo3 = sectionNo2 + sectionLength;
       double sectionNo4 = sectionNo3 + sectionLength;
       double sectionNo5 = sectionNo4 + sectionLength;
       double ballSpeed = Math.sqrt((currentVelocity.getDX() * currentVelocity.getDX())
               + (currentVelocity.getDY() * currentVelocity.getDY()));;

       if (collisionPoint.getX() == blockRec.getUpperLeft().getX()) {
           currentVelocity.setDX(-currentVelocity.getDX());
       }
       if (collisionPoint.getY() == blockRec.getUpperLeft().getY()) {
           currentVelocity.setDY(-currentVelocity.getDY());
       }
       if (collisionPoint.getX() == (blockRec.getUpperLeft().getX() + blockRec.getWidth())) {
           currentVelocity.setDX(-currentVelocity.getDX());
       }
       if (collisionPoint.getY() == (blockRec.getUpperLeft().getY() + blockRec.getHeight())) {
           currentVelocity.setDY(-currentVelocity.getDY());
       }
           if (collisionPoint.getX() >= sectionNo0 && collisionPoint.getX() < sectionNo1) {
               return Velocity.fromAngleAndSpeed(300, ballSpeed);
           } else if (collisionPoint.getX() >= sectionNo1 && collisionPoint.getX() < sectionNo2) {
               return Velocity.fromAngleAndSpeed(330, ballSpeed);
           } else if (collisionPoint.getX() >= sectionNo2 && collisionPoint.getX() < sectionNo3) {
               return currentVelocity;
           } else if (collisionPoint.getX() >= sectionNo3 && collisionPoint.getX() < sectionNo4) {
               return Velocity.fromAngleAndSpeed(30, ballSpeed);
           } else if (collisionPoint.getX() >= sectionNo4 && collisionPoint.getX() <= sectionNo5) {
               return Velocity.fromAngleAndSpeed(60, ballSpeed);
           }
       return currentVelocity;
   }

   /**
    * Add this paddle to the game.
    * @param g a Game object
    */
   public void addToGame(GameLevel g) {
       g.addSprite(this);
       g.addCollidable(this);
   }

     /**
     * Set paddle a new speed.
     * @param newSpeed paddle's new speed.
     */
   public void setSpeed(int newSpeed) {
       this.speed = newSpeed;
   }
}