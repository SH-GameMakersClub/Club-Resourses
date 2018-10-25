package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * The bird that flaps
 */
public class Bird {

    private int size = 40;              // diameter of bird
    private int fallingSpeed = 2;       // acceleration due to gravity
    private int maxFallingSpeed = 16;   // the bird shouldn't fall too fast
    private int jumpStrength = -16;     // negative is up
    private int motionY = 0;            // current up and down motion of Bird
    private Rectangle hitbox;           // hitbox collides with objects in game

    public Bird() {

        // default is center of the screen
        hitbox = new Rectangle(
                FlappyBird.screenWidth  / 2,
                FlappyBird.screenHeight / 2,
                size, size);

    }

    /**
     * Apply the gravity on bird and let it fall. There is a maximum
     * falling speed.
     */
    public void applyGravity() {
        if (motionY < maxFallingSpeed) {
            motionY += fallingSpeed;
        }
    }

    /**
     * Update the state of the bird.
     */
    public void update() {
        hitbox.y += motionY;
        applyGravity();
    }

    /**
     * Draw the bird.
     * @param g 
     */
    public void draw(Graphics g) {

        drawBeak(g);
        drawBody(g);
        drawEye(g);
        drawPupil(g);

    }

    private void drawBeak(Graphics g) {
        g.setColor(Color.yellow);

        /*
        There are three points in the beak: top, bottom, and center
        === Beak visualization ===
        @ <- top (x1, y1)
        o  o
        o     o 
        o        o
        o           @ <- center (x3, y3)
        o        o
        o     o
        o  o
        @ <-bottom (x2, y2)
        */
        
        // array of x-coordinates: x1, x2, x3
        int[] xCoords = {
            (int) hitbox.getMaxX(),
            (int) hitbox.getMaxX(),
            (int) hitbox.getMaxX() + 10
        };

        // array of y-coordinates: y1, y2, y3
        int[] yCoords = {
            (int) hitbox.getY()    + 10,
            (int) hitbox.getMaxY() - 10,
            (int) hitbox.getCenterY()
        };

        Polygon p = new Polygon(xCoords, yCoords, 3);
        g.fillPolygon(p);
    }

    private void drawBody(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(
                hitbox.x,
                hitbox.y,
                hitbox.width,
                hitbox.height);
    }
    
    private void drawEye(Graphics g) {
        g.setColor(Color.white);
        int x = hitbox.x + hitbox.width  * 2/3 - 3;
        int y = hitbox.y + hitbox.height * 1/4;
        int w = hitbox.width             * 1/3;
        int h = hitbox.height            * 1/3;
        g.fillOval(x,y,w,h);
    }
    
    private void drawPupil(Graphics g) {
        g.setColor(Color.black);
        int x = hitbox.x + hitbox.width  * 2/3 + 1;
        int y = hitbox.y + hitbox.height * 1/4 + 2;
        int w = hitbox.width             * 1/5;
        int h = hitbox.height            * 1/5;
        g.fillOval(x,y,w,h);
    }

}
