package flappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;

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
    
    public static int soilHeight = 100;
    public static int grassHeight = 20;
    public static int groundHeight = soilHeight + grassHeight;

    public MyPanel panel;                   // a custom panel to draw onto
    public static FlappyBird flappyBird;    // the game instance
    
    public Bird bird;

    /**
     * Constructor method. Create the game frame.
     */
    public FlappyBird() {

        JFrame frame = new JFrame();

        panel = new MyPanel();
        panel.setPreferredSize(new Dimension(screenWidth,screenHeight));
        frame.add(panel);

        frame.setTitle("Flappy bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
       
        // since this class implements MouseListener, we can just put 'this'
        frame.addMouseListener(this);
        frame.setVisible(true);
        frame.pack();        
         
        bird = new Bird();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create the frame
        flappyBird = new FlappyBird();
        
        // A timer that updates with  20 milliseconds delay
        // or, 50 fps
        Timer timer = new Timer(20, flappyBird);
        timer.start();

    }

    /**
     * Where all the drawing happens.
     *
     * @param g
     */
    public void draw(Graphics g) {

        // blue background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, screenWidth, screenHeight);
        
        // ground
        g.setColor(Color.orange);
        g.fillRect(0, screenHeight - soilHeight, 
                screenWidth, soilHeight);
       
        // grass
        g.setColor(Color.green);
        g.fillRect(0, screenHeight - groundHeight, 
                screenWidth, grassHeight);
        
        bird.draw(g);
    }

    /**
     * The game loop. Any interactions inside the game happens in here.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        bird.update();
        panel.repaint(); // it's very important to 
                         // call repaint after everything is updated
        
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
