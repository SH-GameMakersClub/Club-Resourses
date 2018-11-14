package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * The Bird.
 * 
 * @author Juhyung Kim
 */
public class Bird {

    private int size            = 40;
    private int fallingSpeed    = 2;
    private int maxFallingSpeed = 16;   // we don't want the bird to fall too fast
    private int jumpStrength    = -16;  // negative is up
    private int motionY         = 0;    // current vertical velocity of the bird
    private Rectangle hitbox;

    /**
     * Constructor method to create Bird object.
     */
    public Bird() {

        // put the bird in the center of the screen
        hitbox = new Rectangle(
                    (FlappyBird.screenWidth  / 2) - (size / 2),
                    (FlappyBird.screenHeight / 2) - (size / 2),
                    size,
                    size);

    }

    /**
     * Update the bird's state.
     */
    public void update() {
        
        hitbox.y += motionY; // update the position by its velocity
        applyGravity();      // then apply the gravity
        
    }

    /**
     * Draw the bird.
     *
     * @param g
     */
    public void draw(Graphics g) {

        // the order of drawing matters!
        drawBeak(g);
        drawBody(g);
        drawEye(g);
        drawPupil(g);

    }

    /**
     * Draw the pupil of the bird.
     * @param g 
     */
    private void drawPupil(Graphics g) {
        
        int x = hitbox.x + hitbox.width  * 2 / 3 + 1;
        int y = hitbox.y + hitbox.height * 1 / 4 + 2;
        int w = hitbox.width             * 1 / 5;
        int h = hitbox.height            * 1 / 5;
                
        g.setColor(Color.BLACK);
        g.fillOval(x, y, w, h);
    
    }

    /**
     * Draw the eye of the bird
     * @param g 
     */
    private void drawEye(Graphics g) {
        
        int x = hitbox.x + hitbox.width  * 2 / 3 - 3;
        int y = hitbox.y + hitbox.height * 1 / 4;
        int w = hitbox.width             * 1 / 3;
        int h = hitbox.height            * 1 / 3;
           
        g.setColor(Color.WHITE);
        g.fillOval(x, y, w, h);
        
    }

    /**
     * Draw the body of the bird.
     * @param g 
     */
    private void drawBody(Graphics g) { 
        
        g.setColor(Color.RED);
        g.fillOval(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        
    }

    /**
     * Draw the beak of the bird.
     * @param g 
     */
    private void drawBeak(Graphics g) {
        
        // arrays of points that create the triangluar beak
        int[] xCoords = {(int) hitbox.getMaxX(),
                         (int) hitbox.getMaxX(),
                         (int) hitbox.getMaxX() + 10};
        
        int[] yCoords = {(int) hitbox.getY()    + 10,
                         (int) hitbox.getMaxY() - 10,
                         (int) hitbox.getCenterY()};
        
        Polygon beak = new Polygon(xCoords, yCoords, 3);
        
        // the beak
        g.setColor(Color.yellow);
        g.fillPolygon(beak);
        
    }

    /**
     * The bird falls every frame due to the gravity.
     */
    public void applyGravity() {
        if (motionY < maxFallingSpeed) {
            motionY += fallingSpeed;
        }
    }

    /**
     * The bird jumps.
     */
    public void jump() {
        motionY = jumpStrength;
    }

    /**
     * Reset the position of the bird.
     */
    public void resetPosition() {

        // only need to reset the Y coordinate since X coordinate never changes
        hitbox.y = FlappyBird.screenHeight / 2 - size / 2;
        motionY = 0;

    }

    /**
     * Get the hitbox of the bird.
     *
     * @return
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

}
