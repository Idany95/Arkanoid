package basics;
import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import game.Sprite;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v = new Velocity(0, 0);
    private GameEnvironment gameEnv;

    /**
     * A Ball's object constructor.
     * @param center Ball's center Point
     * @param r Ball's radius
     * @param color Ball's color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * A Ball's object constructor.
     * @param x Ball's center Point's X-axis location
     * @param y Ball's center Point's Y-axis location
     * @param r Ball's radius
     * @param color Ball's color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * A Ball's object constructor.
     * @param x Ball's center Point's X-axis location
     * @param y Ball's center Point's Y-axis location
     * @param r Ball's radius
     * @param color Ball's color
     * @param angle Ball's angle
     * @param speed Ball's speed
     */
    public Ball(int x, int y, int r, java.awt.Color color, double angle, double speed) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        Velocity tempV = Velocity.fromAngleAndSpeed(angle, speed);
        this.setVelocity(tempV);
    }

    /**
     * Method for getting Ball's center X-axis location.
     * @return int Ball's center X-axis location
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Method for getting Ball's center Y-axis location.
     * @return int Ball's center Y-axis location
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Method for getting Ball's radius.
     * @return int Ball's radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Method for getting Ball's center.
     * @return Point Ball's center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Method for getting Ball's color.
     * @return java.awt.Color Ball's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Method for drawing a Ball on a given DrawSurface.
     * @param surface current DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(java.awt.Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Method for setting a Ball's velocity.
     * @param newV wanted velocity
     */
    public void setVelocity(Velocity newV) {
        this.v = newV;
    }

    /**
     * Method for setting a Ball's velocity (by dx and dy).
     * @param dx change in x-Axis
     * @param dy change in y-Axis
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Method for getting a Ball's velocity.
     * @return Velocity Ball's velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Method for moving a ball one step.
     * this while making sure it's not going out of boundaries.
     */
    public void moveOneStep() {
        CollisionInfo colInfo, nextColInfo;
        colInfo = this.getGameEnvironment().getClosestCollision(this.trajectory());
        if (colInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.getCenter());
        } else {
            this.setVelocity(colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.getVelocity()));
            // get next collision info
            Ball ballAfterOneStep = new Ball((int) (this.getCenter().getX() + this.getVelocity().getDX()),
                    (int) (this.getCenter().getY() + this.getVelocity().getDY()),
                    this.getSize(), java.awt.Color.BLACK);
            nextColInfo = this.getGameEnvironment().getClosestCollision(ballAfterOneStep.trajectory());
            // check if next collision info is null
            //if (nextColInfo == null) {
            //    return;
            //}
            Rectangle nextColRec = nextColInfo.collisionObject().getCollisionRectangle();
            // check if ball center + dx dy will be inside the collisionshape
            // move the ball only if the next move is legall.i.e the ball isn't in another rectangle
            if (!((ballAfterOneStep.getCenter().getX() >= nextColRec.getUpperLeft().getX()
                || ballAfterOneStep.getCenter().getY() >= nextColRec.getUpperLeft().getY()
                || ballAfterOneStep.getCenter().getX() <= (nextColRec.getUpperLeft().getX() + nextColRec.getWidth())
                || ballAfterOneStep.getCenter().getY() <= (nextColRec.getUpperLeft().getY() + nextColRec.getHeight()))
                && (colInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY() != 560))) {
                this.center = this.getVelocity().applyToPoint(this.getCenter());
            }
        }
    }

    /**
     * Access method for getting Ball object's GameEnvironment.
     * @return Ball Object's GameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnv;
    }

    /**
     * Method for setting Ball object's GameEnvironment.
     * @param newGameEnv Ball object's new GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment newGameEnv) {
        this.gameEnv = newGameEnv;
    }

    /**
     * Method for calculating Ball object's trajectory.
     * @return Line Ball object's trajectory
     */
    public Line trajectory() {
        Point end = this.getVelocity().applyToPoint(this.getCenter());
        Line ballTrajectory = new Line(this.getCenter(), end);
        return ballTrajectory;
    }

    /**
     * Method for calling Ball's moveOneStep method when time is passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Method for adding a Ball object to a game.
     * @param g Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Method for removing a Ball object to a game.
     * @param g Game object
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}