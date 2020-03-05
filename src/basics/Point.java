package basics;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Point {
    private double x;
    private double y;

    /**
     * A Point object constructor.
     * @param x point's X-axis location
     * @param y point's Y-axis location
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method made for calculating the distance between two points.
     * @param other the other point
     * @return double The distance between two points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * Method made for checking if two points are equal.
     * @param other the other point
     * @return boolean returns true if the points are equals, false otherwise
     */
    public boolean equals(Point other) {
        if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Method made for getting a Point object's X.
     * @return double returns a Point object's X
     */
    public double getX() {
        return this.x;
    }

    /**
     * Method made for getting a Point object's Y.
     * @return double returns a Point object's Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Method made for setting a Point object's X.
     * @param newX Point's new X
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Method made for setting a Point object's Y.
     * @param newY Point's new Y
     */
    public void setY(double newY) {
        this.y = newY;
    }
}