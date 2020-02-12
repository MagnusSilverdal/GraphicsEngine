package samples;

import java.awt.*;

/**
 * This is a class
 * Created 2020-02-12
 *
 * @author Magnus Silverdal
 */
public class Julia implements Runnable{
    private double xmin = -1.5;
    private double xmax = 1.5;
    private double ymin = -1;
    private double ymax = 1;
    private double dx, dy;
    private int width, height;
    private int[] palette;
    private int maxIter = 1024;
    private int[] pixels;
    private Thread t;
    protected boolean running = false;
    private boolean calculating = false;

    /**
     * Constructs a setup for the julia generator. A palette of colours is generated using the hue as variable
     * (in the hsb-color model, hue is an angle rotating full circle giving a nice circular palette)
     * @param w int determining the width of the image
     * @param h int determining the height of the image
     */
    public Julia(int w, int h) {
        this.width = w;
        this.height = h;
        pixels = new int[w*h];
        dx = (xmax-xmin)/(width-1);
        dy = (ymax-ymin)/(height-1);
        palette = new int[256];
        for (int i = 0; i < 256; i++)
            palette[i] = Color.getHSBColor(i/255F, 1, 1).getRGB();
    }

    /**
     * Method to adjust the region in the complex plane to be used for calculations, region is scaled to
     * fit image dimension
     * @param xmin minimum real value
     * @param xmax maximum real value
     * @param ymin minimum imaginary value
     * @param ymax maximum imagonary value
     */
    public void setBound(double xmin,double xmax,double ymin,double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    /**
     * Method to generate the julia image. Loops through all points (pixels) and calculates the color, based
     * on the number of iterations needed to decide weather the iterated function at the given point converges or not.
     */
    public void generateJulia(double cx, double cy) {
        double x, y;
        for (int row = 0; row < height; row++) {
            y = ymax - dy*row;
            for (int col = 0; col < width; col++) {
                x = xmin + dx*col;
                pixels[row*width+col]=julia(x,y,cx,cy);
            }
        }
    }

    /**
     * Getter for the image data
     * @return an array of ints representing the julia image
     */
    public int[] getJuliaImage() {
        return pixels;
    }

    /**
     * Method to calculate the number of iterations needed to decide weather the function converges at x+iy
     * @param x the real number
     * @param y the imaginary number
     * @param cx the real number
     * @param cy the imaginary number
     * @return the number of iterations needed to analyse the point x+iy
     */
    private int julia(double x, double y, double cx, double cy) {
        int count = 0;
        double xn = x;
        double yn = y;
        double newxn;

        while (count < maxIter && (xn*xn + yn*yn) < 4) {
            count++;
            newxn = xn*xn - yn*yn + cx;
            yn = 2*xn*yn + cy;
            xn = newxn;
        }
        if (count == maxIter)
            return 0;
        else
            return palette[count%palette.length];
    }

    public synchronized void start() {
        running = true;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        calculating = true;
        generateJulia(-0.4,0.6);
    }
}
