package flappybird;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 * The game mechanics are in here. Implemented ActionListener and MouseListener
 * for user inputs.
 *
 * @author Juhyung Kim
 */
public class FlappyBird implements ActionListener, MouseListener {

    // dimensions of the game frame (flappy bird is vertically longer)
    public static int screenWidth = 600;
    public static int screenHeight = 800;

    public MyPanel panel; // a custom panel to draw onto
    public static FlappyBird flappyBird; // the game instance

    /**
     * Constructor method. Create the game frame.
     */
    public FlappyBird() {

        JFrame frame = new JFrame();

        panel = new MyPanel();
        frame.add(panel);

        frame.setTitle("Flappy bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        // since this class implements MouseListener, we can just put 'this'
        frame.addMouseListener(this);
        frame.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create the frame
        flappyBird = new FlappyBird();

    }

    /**
     * Where all the drawing happens.
     *
     * @param g
     */
    public void draw(Graphics g) {

    }

    /**
     * The game loop. Any interactions inside the game happens in here.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    /**
     * Fire when mouse is released (Basically same as click).
     *
     * We use mouseReleased for mouse click input. This method is more
     * "accurate" on catching click input than mouseClicked.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // unused implemented method
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    // unused implemented method
    @Override
    public void mousePressed(MouseEvent e) {
    }

    // unused implemented method
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // unused implemented method
    @Override
    public void mouseExited(MouseEvent e) {
    }

}
