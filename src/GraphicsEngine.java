import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * The container for the graphics, based on a Swing Canvas and using an int-array to control pixels.
 * Everything in the GraphicsEngine library is separated from Swing, and at a later stage Swing can be
 * replaced by another technique to access the graphics-hardware.
 */

public class GraphicsEngine extends Canvas{
    public static int WIDTH = 800;
    public static int HEIGTH = WIDTH*9/16;
    private JFrame frame;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    /**
     * Constructor to create a JFrame and add the GraphicsEngine to it.
     * @param title is the title in the windowbar
     */
    public GraphicsEngine(String title) {
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * A main to enable testing of the graphics
     * @param args not used at this stage
     */
    public static void main(String[] args) {
        GraphicsEngine ge = new GraphicsEngine("Graphics Engine test");
    }
}
