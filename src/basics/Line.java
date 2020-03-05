package basics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class Line {
    private Point start;
    private Point end;
    private double errorMargin = 0.1;

    /**
     * A Line object constructor.
     * @param start Line's start point
     * @param end Line's end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A Line object constructor.
     * @param x1 first Point's X-axis location
     * @param y1 first Point's Y-axis location
     * @param x2 second Point's X-axis location
     * @param y2 second Point's Y-axis location
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Method made for calculating Line objects' length.
     * @return double Line's length
     */
    public double length() {
        return this.start.distance(this.end());
    }

    /**
     * Method made for finding Line objects' middle Point.
     * @return Point Line's middle Point
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Method made for getting Line objects' start Point.
     * @return Point Line's start Point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Method made for getting Line objects' end Point.
     * @return Point Line's end Point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Method made for finding out if two Lines intersect.
     * @param other the other Line
     * @return boolean true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Method made for finding two Line's intersection Point.
     * @param other the other Line
     * @return Point Returns the intersection point if the lines intersect, and
     *         null otherwise.
     */
    public Point intersectionWith(Line other) {
        /* calculating each line's m (slope) and n (intersection with Y-axis) */
        double m1 = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
        double n1 = m1 * (-this.end().getX()) + this.end().getY();
        double m2 = (other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX());
        double n2 = (m2 * (-other.end().getX())) + other.end().getY();
        /* calculating intersection point */
        double intersectionX = (n2 - n1) / (m1 - m2);
        double intersectionY = m1 * intersectionX + n1;
        /* a private solution for vertical lines */
        //if ((this.end().getX() - this.start().getX() == 0)
        //        && ((other.end().getX() - other.start().getX()) == 0)
        //        && (this.start().getX() != other.start().getX())) {
        //    return null;
        //}
        if ((this.end().getX() - this.start().getX() == 0)) {
            intersectionX = this.start.getX();
            intersectionY = m2 * intersectionX + n2;
        }
        if ((other.end().getX() - other.start().getX()) == 0) {
            intersectionX = other.start().getX();
            intersectionY = m1 * intersectionX + n1;
        }
        Point intersectionPoint = new Point(intersectionX, intersectionY);
        /*
         * we considered our lines to be functions, now we make sure the point we found
         * actually is on the lines. this is done by triangle inequality
         */
        if ((Math.abs(this.start().distance(this.end())
               - (this.start().distance(intersectionPoint) + this.end().distance(intersectionPoint)))
                    >= errorMargin)
              || Math.abs(other.start().distance(other.end())
               - (other.start().distance(intersectionPoint) + other.end().distance(intersectionPoint)))
                  >= errorMargin) {
            return null;
        }
        return intersectionPoint;
    }

    /**
     * Method made for finding out if two Lines are equals.
     * @param other the other Line
     * @return boolean return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this == other) {
            return true;
        }
        return false;
    }

    /**
     * Return closest intersection to start of the line.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect a Rectangle
     * @return Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double shortestDistance;
        Point closestIntersectionPoint;
        List<Point> pointList = new ArrayList<Point>();
        pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        } else {
            shortestDistance = this.start().distance(pointList.get(0));
            closestIntersectionPoint = pointList.get(0);
            for (int i = 1; i < pointList.size(); i++) {
                if (this.start().distance(pointList.get(i)) < shortestDistance) {
                    shortestDistance = this.start().distance(pointList.get(i));
                    closestIntersectionPoint = pointList.get(i);
                }
            }
            return closestIntersectionPoint;
        }
    }
}