package TwoDPrimitives;

/**
 * An line represented by a start- and an endpoint. A line can have colour but if omitted it is defaulted to white
 */
public class Line {
    private Point start;
    private Point end;
    private int col;

    /**
     * Constructor setting the start and endpoints
     * @param s the startpoint
     * @param e the endpoint
     */
    public Line(Point s, Point e) {
        this.start = s;
        this.end = e;
        this.col = 0xFFFFFF;
    }

    /**
     * Constructor setting the start and endpoints and color
     * @param s
     * @param e
     * @param col
     */
    public Line(Point s, Point e, int col) {
        this.start = s;
        this.end = e;
        this.col = col;
    }

    /**
     * Method to draw the line on the screen. This is an implementation of the
     * @see <a href="https://en.wikipedia.org/wiki/Digital_differential_analyzer_(graphics_algorithm)">DDA algorithm</a>
     * @param pixels the accessible pixels store in an int-array
     * @param width of the drawable area as an int
     */
    public void draw(int[] pixels, int width) {
        int dx,dy,k;
        double xc,yc,x,y,steps;
        Point drawablePoint;
        dx=end.getX()-start.getX();
        dy=end.getY()-start.getY();
        if(Math.abs(dx)>Math.abs(dy))
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);
        xc=(dx/steps);
        yc=(dy/steps);
        x=start.getX();
        y=start.getY();
        drawablePoint = new Point((int)x,(int)y,col);
        for(k=1;k<=steps;k++) {
            x=x+xc;
            y=y+yc;
            drawablePoint.setX((int)x);
            drawablePoint.setY((int)y);
            drawablePoint.draw(pixels,width);
        }
    }
}
