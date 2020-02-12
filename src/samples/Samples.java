package samples;

import TwoDPrimitives.Line;
import TwoDPrimitives.Point;
import GarphicsEngine.Screen;

/**
 * Examples of the graphics engine in action.
 */
public class Samples {
    private int state = 1;
    private int counter = 0;
    private int width;
    private int height;
    private boolean updated = false;

    private Point[] points = new Point[10];
    private Mandelbrot m;
    private Julia j;
    private Thread t;
    private MovingLine ml;

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
        j = new Julia(width, height);
        ml = new MovingLine();
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
                    updated = true;
                }
                counter++;
                break;
            case 1:
                // start a thread to calculate a mandelbrot set
                if (!m.running) {
                    m.start();
                }
                if (counter > 300) {
                    state++;
                    counter = 0;
                    updated = true;
                }
                counter++;
                break;
            case 2:
            // start a thread to calculate a julia set
            if (!j.running) {
                j.start();
            }
            if (counter > 300) {
                state++;
                counter = 0;
                updated = true;
            }
            counter++;
            break;
            case 3:
                // Animate a line
                ml.move();
                updated = true;
                counter++;
                break;
        }
    }

    /**
     * Render the pixels from the example to the screen
     * @param screen int[] representing the pixels of the screen
     */
    public void render(Screen screen) {
        switch (state) {
            case 0:
                for (int i = 0; i < 10; i++) {
                    points[i].draw(screen);
                }
                break;
            case 1:
                if (updated) {
                    clear(screen);
                    updated = false;
                }
                int[] p = m.getMandelbrotImage();
                int[] pixels = screen.getPixels();
                for (int i = 0 ; i < p.length ; i++) {
                    pixels[i] = p[i];
                }
                break;
            case 2:
                if (updated) {
                    clear(screen);
                    updated = false;
                }
                p = j.getJuliaImage();
                pixels = screen.getPixels();
                for (int i = 0 ; i < p.length ; i++) {
                    pixels[i] = p[i];
                }
                break;
            case 3:
                if (updated) {
                    if (counter == 1) {
                        clear(screen);
                    } else {
                        fade(screen);
                    }
                }
                Line l = ml.getLine();
                l.draw(screen);
                break;
            default:
        }
    }

    /**
     * Clear the screen by sett in all pixels to 0
     * @param s An the Screen containing an array of ints representing pixels on display
     */
    private void clear(Screen s) {
        int[] pixels = s.getPixels();
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = 0;
        }
    }

    /**
     * Clear the screen by sett in all pixels to 0
     * @param s An the Screen containing an array of ints representing pixels on display
     */
    private void fade(Screen s) {
        int [] pixels = s.getPixels();
        // 0x010101
        int fade =(65536+256+1);
        fade *=2;
        for (int i = 0 ; i < pixels.length ; i++) {
            if (pixels[i] > fade) {
                pixels[i] -= fade;
            }
        }
    }
}
