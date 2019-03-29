package samples;

import TwoDPrimitives.Line;
import TwoDPrimitives.Point;

/**
 * A demonstration of the Line. A line moving around within a rectangle on the screen
 */
public class MovingLine {
    private Line l;
    private Point start;
    private Point end;
    private int state = 0;
    private int xmin = 200;
    private int xmax = 600;
    private int ymin = 200;
    private int ymax = 600;

    /**
     * Constructor creates the start and endpoint of the line and the line itself
     */
    public MovingLine() {
        start = new Point(xmin,ymin);
        end = new Point(xmax,ymin);
        l = new Line(start, end);
    }

    /**
     * Moves the line around, changing direction when it reaches the borders of the bounding rectangle
     */
    public void move() {
        switch(state) {
            case 0:
                start.setX(start.getX()+10);
                end.setY(end.getY()+10);
                if (start.getX() == xmax)
                    state = 1;
                break;
            case 1:
                start.setY(start.getY()+10);
                end.setX(end.getX()-10);
                if (start.getY() == ymax)
                    state = 2;
                break;
            case 2:
                start.setX(start.getX()-10);
                end.setY(end.getY()-10);
                if (start.getX() == xmin)
                    state = 3;
                break;
            case 3:
                start.setY(start.getY()-10);
                end.setX(end.getX()+10);
                if (start.getY() == ymin)
                    state = 0;
                break;
        }
    }

    /**
     * Getter for the line
     * @return the Line
     */
    public Line getLine() {
        return l;
    }

}
