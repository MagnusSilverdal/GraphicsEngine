import samples.Mandelbrot;

/**
 * A class representing the pixel of the graphics as an integer-array. All updating and rendering
 * is done relative to this class
 */
public class Screen {
    private int width;
    private int height;
    private int[] pixels;

    /**
     * Constructor for the Screen. Creates an array with all pixels of the display, width x height.
     * @param w width of the screen
     * @param h height of the screen
     */
    public Screen(int w, int h) {
        this.width = w;
        this.height = h;
        this.pixels = new int[w*h];
    }

    /**
     * Updates all elements of the graphics. Called before the rendering-method renders them into the pixel-array
     */
    public void update() {

    }

    /**
     * Renders all elements of the graphics. Called after the update-method is done updating all elements
     */
    public void render() {
        // Randomly scatter magenta coloured pixels on the screen
        pixels[(int)(Math.random()*(height-1)*width + Math.random()*width)] = 0xFF00FF;
    }

    /**
     * Getter for the pixel-array
     * @return int[] containing pixelvalues
     */
    public int[] getPixels() {
        return pixels;
    }

    /**
     * Calculate and generate a pixelarray representing the mandelbrot swet
     */
    private void generateMandelbrot() {
        Mandelbrot mb = new Mandelbrot(width,height);
        mb.generateMandelbrot();
        pixels = mb.getMandelbrotImage();
    }
}
