
package flappybird;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The custom panel to draw stuff on.
 * 
 * @author Juhyung Kim
 */
public class MyPanel extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        // call the draw method in our flappybird instance
        FlappyBird.flappyBird.draw(g);
        
    }
    
}
