import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * The container for the graphics, based on a Swing Canvas and using an int-array to control pixels.
 * Everything in the GraphicsEngine library is separated from Swing, and at a later stage Swing can be
 * replaced by another technique to access the graphics-hardware.
 */
public class GraphicsEngine extends Canvas implements Runnable{
    public static int WIDTH = 800;
    public static int HEIGTH = WIDTH*9/16;
    private String title;
    private JFrame frame;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Screen screen;
    private Thread thread;
    private boolean running = false;

    /**
     * Constructor to create a JFrame and add the GraphicsEngine to it.
     * @param title is the title in the windowbar
     */
    public GraphicsEngine(String title) {
        screen = new Screen(WIDTH, HEIGTH);
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        frame = new JFrame();
        frame.setResizable(false);
        this.title = title;
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Implementing the start method in the Runnable interface. Creates a thread of the canvas and starts it
     */
    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    /**
     * Implementation of the stop() method in the Runnable interface. Brings everything to a controlled stop.
     */
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The run method from the Runnable interface implemented. A series of updates ande renders are executed based on
     * a timer. The animation speed is set to 60 ups but the fps is running as fast as possible. To make sure nothing
     * is dropped multiple updates can be done if neede but there is no way of "frame skipping" if updates
     * are too time consuming.
     */
    @Override
    public void run() {
        double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(this.title + "  |  " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    /**
     * Update all screen data. Calls the update method in Screen
     */
    private void update() {
        screen.update();
    }

    /**
     * Renders the graphics to the screen. Using a buffer strategy the Screen render method i called and then all data
     * is copied to the image which is finally drawn on the screen
     */
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();
        int[] screenPixels = screen.getPixels();

        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = screenPixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH, HEIGTH, null);
        g.dispose();
        bs.show();
    }

    /**
     * A main to enable testing of the graphics
     * @param args not used at this stage
     */
    public static void main(String[] args) {
        GraphicsEngine ge = new GraphicsEngine("Graphics Engine test");
        ge.start();
    }
}
