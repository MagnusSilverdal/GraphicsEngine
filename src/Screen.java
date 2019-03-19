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
     * @param w width och the screen
     * @param h height oif the screen
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
        // Just a test. Draw a white square at (100,100), 100 pixels wide
        for (int y = 100 ; y < 200 ; y++) {
            for (int x = 100 ; x < 200 ; x++) {
                pixels[y*width + x] = 0xFFFFFF;
            }
        }
    }

    /**
     * Getter for the pixel-array
     * @return int[] containing pixelvalues
     */
    public int[] getPixels() {
        return pixels;
    }
}
