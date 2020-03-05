package basics;
/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * A Velocity object constructor.
     * @param dx delta X
     * @param dy delta Y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Method made for applying a velocity on a Point.
     * @param p the start Point on which the velocity will be applied upon.
     * @return Point start Point after velocity had been applied.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Method made for getting velocity's dx.
     * @return double velocity's dx
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Method made for getting velocity's dy.
     * @return double velocity's dy
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Method made for setting velocity's dx.
     * @param newDX velocity's new dx
     */
    public void setDX(double newDX) {
        this.dx = newDX;
    }

    /**
     * Method made for setting velocity's dy.
     * @param newDY velocity's new dy
     */
    public void setDY(double newDY) {
        this.dy = newDY;
    }

    /**
     * Method made for setting velocity dx and dy by angle and speed.
     * @param angle wanted angle
     * @param speed wanted speed
     * @return Velocity updated Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }
}