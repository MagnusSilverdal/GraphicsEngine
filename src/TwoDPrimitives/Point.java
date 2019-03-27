package TwoDPrimitives;

/**
 * Point representation i 2D. A point knows it's coordinates (x,y) and if needed a colour.
 */
public class Point {
    private int x;
    private int y;
    private int col;

    /**
     * Creates a white point at (x,y)
     * @param x x-coordinate of Point
     * @param y y-coordinate of Point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.col = 0xFFFFFF;
    }

    /**
     * Creates a Point with a given colour at a given coordinate.
     * @param x x-coordinate of Point
     * @param y y-coordinate of Point
     * @param col colour of point in hex
     */
    public Point(int x, int y, int col) {
        this.x = x;
        this.y = y;
        this.col = col;
    }

    /**
     * Changes the colour of the Point
     * @param col new colour in hex
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Draws the Point to a screen. Sets the corresponding index in the int array to the colour of the point
     * @param screen integer array of pixel data for the screen
     * @param width the width of the screen in pixels. Needed as offset in the array.
     */
    public void draw(int[] screen, int width) {
        if (y * width + x < screen.length) {
            screen[y * width + x] = col;
        }
    }

    /**
     * Getter for x-ccordinate
     * @return in, the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y-ccordinate
     * @return in, the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for x-coordinate
     * @param x new coordinate as int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for y-coordinate
     * @param y new coordinate as int
     */
    public void setY(int y) {
        this.y = y;
    }
}
