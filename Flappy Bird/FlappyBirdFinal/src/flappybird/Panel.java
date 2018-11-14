package flappybird;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Where we draw stuff.
 * 
 *  @author Juhyung Kim
 */
public class Panel extends JPanel {

    /**
     * A built-in method from JPanel that'll allow us to draw on screen.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (FlappyBird.flappyBird != null) {
            FlappyBird.flappyBird.draw(g);
        }
    }

}
