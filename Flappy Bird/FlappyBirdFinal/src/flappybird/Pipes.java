package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * A pair of pipes.
 *
 * @author Juhyung Kim
 */
public class Pipes {

    public static int horizontalGap = 300; // gap between adjacent pipes

    public int verticalGap  = 200;   // fixed sized gap between top and bottom pipes
    public boolean passed   = false; // true if the bird has passed this pipe
    public boolean addPipe  = true;  // true if ready to add a following Pipes to screen 

    private int speed           = 8;
    private int width           = 100;
    private int height;                 // of the bottom pipe (assigned later)
    private int minimumHeight   = 100;  // we don't want the pipe to be too short
    private int heightRange     = 280;  // the range of pipe's random height
    private Rectangle top, bottom;      // the pipes' hitboxes

    /**
     * Constructor method to create Pipes object.
     */
    public Pipes() {
        
        // height of the bottom pipe is set randomly
        int randomHeight = new Random().nextInt(heightRange);
        // but it should be taller than minimum height
        height = minimumHeight + randomHeight;

        bottom = new Rectangle(
                FlappyBird.screenWidth,
                FlappyBird.screenHeight - FlappyBird.groundHeight - height,
                width,
                height);

        top = new Rectangle(
                FlappyBird.screenWidth,
                0,
                width,
                bottom.y - verticalGap);

    }

    /**
     * Update the pipe's state
     *
     * @param bird Bird object to determine its hitbox
     */
    public void update(Bird bird) {

        // move towards left
        top.x    -= speed;
        bottom.x -= speed;

        // check if the bird passed them
        if (passed == false
                && bird.getHitbox().getCenterX() > top.getMaxX()) {
            passed = true;
            FlappyBird.score++;
        }
    }

    /**
     * Draw the pipe on screen.
     *
     * @param g
     */
    public void draw(Graphics g) {

        g.setColor(Color.green.darker());

        g.fillRect(top.x,    top.y,     top.width,    top.height);
        g.fillRect(bottom.x, bottom.y,  bottom.width, bottom.height);

    }

    /**
     * Get the X coordinate of the right side of the pipe.
     *
     * @return
     */
    public int getMaxX() {
        return (int) top.getMaxX();
    }

    /**
     * Chech if this pipes have collided with given hitbox.
     *
     * @param hitbox The bird's hitbox
     * @return true if collided
     */
    boolean isCollided(Rectangle hitbox) {

        boolean collided;

        if (hitbox.intersects(top) || hitbox.intersects(bottom)) {
            collided = true;
        } else {
            collided = false;
        }

        return collided;
    }

}
