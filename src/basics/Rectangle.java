package basics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Rectangle constructor method.
     * @param upperLeft Rectangle's upper left corner
     * @param width Rectangle's width
     * @param height Rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Rectangle constructor method.
     * @param x upper left corner's x
     * @param y upper left corners' y
     * @param width Rectangle's width
     * @param height Rectangle's height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line check intersection with parameter line
     * @return a List of Points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<Point>();
        double upperLeftX = this.upperLeft.getX();
        double upperLeftY = this.upperLeft.getY();
        Line leftRecLine = new Line(upperLeftX, upperLeftY, upperLeftX, upperLeftY + this.getHeight());
        Line topRecLine = new Line(upperLeftX, upperLeftY, upperLeftX + this.getWidth(), upperLeftY);
        Line bottomRecLine = new Line(upperLeftX, upperLeftY + this.getHeight(),
                                    upperLeftX + this.getWidth(), upperLeftY + this.getHeight());
        Line rightRecLine = new Line(upperLeftX + this.getWidth(), upperLeftY,
                                    upperLeftX + this.getWidth(), upperLeftY + this.getHeight());
        if (leftRecLine.isIntersecting(line)) {
            pointList.add(leftRecLine.intersectionWith(line));
        }
        if (topRecLine.isIntersecting(line)) {
            pointList.add(topRecLine.intersectionWith(line));
        }
        if (bottomRecLine.isIntersecting(line)) {
            pointList.add(bottomRecLine.intersectionWith(line));
        }
        if (rightRecLine.isIntersecting(line)) {
            pointList.add(rightRecLine.intersectionWith(line));
        }
        return pointList;
    }

    /**
     * Return the width of the Rectangle.
     * @return double height
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the Rectangle.
     * @return double height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return Point upperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set UpperLeft field's X-axis location.
     * @param newX new UpperLeft field's X-axis location
     */
    public void setUpperLeftX(double newX) {
        this.upperLeft.setX(newX);
    }
}
