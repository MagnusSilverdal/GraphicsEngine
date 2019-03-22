package samples;

import TwoDPrimitives.Point;

/**
 * Examples of the graphics enigine in action.
 */
public class Samples {
    private int state = 0;
    private int counter = 0;
    private int width;
    private int height;

    Point[] points = new Point[10];
    Mandelbrot m;
    Thread t;

    /**
     * Setting up all relevant variables.
     */
    public Samples(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < 10; i++) {
            points[i] = new Point((int) (Math.random() * (width)), (int) (Math.random() * (height)), 0xFF00FF);
        }
         m = new Mandelbrot(width,height);
    }

    /**
     * Updates the current visual example.
     */
    public void update() {
        switch (state) {
            case 0:
                // Randomly scatter magenta coloured pixels on the screen
                for (int i = 0; i < 10; i++) {
                    points[i] = new Point((int) (Math.random() * (width)), (int) (Math.random() * (height)), 0xFF00FF);
                }
                if (counter > 600) {
                    state++;
                    counter = 0;
                }
                counter++;
                break;
            case 1:
                if (!m.running) {
                    m.start();
                }
                if (counter > 300) {
                    state++;
                    counter = 0;
                }
                counter++;
                break;
            case 2:

        }
    }

    /**
     * Render the pixels from the example to the screen
     * @param pixels int[] representing the pixels of the screen
     */
    public void render(int[] pixels) {
        switch (state) {
            case 0:
                for (int i = 0; i < 10; i++) {
                    points[i].draw(pixels, width);
                }
                break;
            case 1:
                int[] p = m.getMandelbrotImage();
                for (int i = 0 ; i < p.length ; i++) {
                    pixels[i] = p[i];
                }
                break;
            case 2:
                for (int i = 0 ; i < pixels.length ; i++) {
                    pixels[i] = 0;
                }
        }
    }
}
