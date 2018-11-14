package flappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The class where the game logic runs.
 *
 * @author Juhyung Kim
 */
public class FlappyBird implements ActionListener, MouseListener {

    public static int screenWidth   = 600;
    public static int screenHeight  = 800;
    public static int grassHeight   = 20;
    public static int soilHeight    = 100;
    public static int groundHeight  = grassHeight + soilHeight;
    public static int score         = 0;
    public static FlappyBird flappyBird; // the game instance

    public Panel    panel;               // where we draw stuff
    public Bird     bird;
    public boolean  gameOver = false;
    public boolean  started  = false;    // true if game is started and running
    public List<Pipes> pipes;            // a list of pipes in the game

    public FlappyBird() {

        // the game frame
        JFrame frame = new JFrame();

        // we'll draw the game screen on this panel
        panel = new Panel();
        // it's important that the screen dimension is the dimension of the panel,
        // not the frame
        panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.add(panel);

        frame.setTitle("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this); // allow the game to receive mouse action
        frame.setResizable(false); // we don't want people to resize our game's screen
        frame.setVisible(true);
        frame.pack(); // update the frame to fit the panel

        // set the location of the frame to center of the screen
        frame.setLocationRelativeTo(null);

        bird = new Bird();
        pipes = new ArrayList<>();

        addPipes(); // a pair of pipes is added at the start of the game

    }

    /**
     * Where the game starts.
     *
     * @param args
     */
    public static void main(String[] args) {

        // create the game instance
        flappyBird = new FlappyBird();

        // timer for the game loop with 20 millisec delay (50 fps)
        Timer timer = new Timer(20, flappyBird);
        timer.start();
    }

    /**
     * Add a pair of pipe to the screen.
     */
    public void addPipes() {
        pipes.add(new Pipes());
    }

    /**
     * Check if a new pair of pipe is needed to be added in the screen. 
     * If it is needed, add one.
     */
    public void checkAddNewPipe() {

        for (int i = 0; i < pipes.size(); i++) {

            Pipes currentPipe = pipes.get(i);

            if (currentPipe.addPipe == true) {
                if (currentPipe.getMaxX() <= screenWidth - Pipes.horizontalGap) {
                    addPipes();
                    currentPipe.addPipe = false;
                    break;
                }
            }

        }

    }

    /**
     * Check if the bird is collided to pipes, bottom, or top.
     *
     * @return true if the bird has collided to any of them
     */
    public boolean isCollided() {

        boolean collided = false;
        Rectangle hitbox = bird.getHitbox();

        // if hits pipe
        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).passed == false) {
                if (pipes.get(i).isCollided(hitbox) == true) {
                    collided = true;
                    break;
                }
            }
        }

        if (collided == false) {
            // if hits bottom or top
            if (hitbox.getMaxY() > screenHeight - groundHeight
                    || hitbox.y < 0) {
                collided = true;
            }
        }

        return collided;

    }

    /**
     * Reset the game.
     */
    public void resetGame() {

        bird.resetPosition();
        pipes.clear();
        addPipes();
        gameOver = false;
        started = false;
        score = 0;

    }

    /**
     * The game loop: where everything inside the game udpate.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // we only want to update the screen when 
        // the game have started and is not over yet
        if (gameOver == false && started == true) {

            for (int i = 0; i < pipes.size(); i++) {
                pipes.get(i).update(bird);
            }

            checkAddNewPipe();
            bird.update();

            boolean collided = isCollided();

            // game over
            if (collided == true) {
                gameOver = true;
            }

        }

        // update the drawing at last
        panel.repaint();

    }

    /**
     * Fire when mouse is released.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        // using else-if's to only allow on statement to be executed at a time
        // for example, we don't want the bird jump and reset the game at the same time
        if (gameOver == true) {
            resetGame();
        } else if (started == false) {
            started = true;
        } else {
            bird.jump();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Where all the drawing on the screen happens.
     *
     * @param g Graphics object we use to draw stuff
     */
    public void draw(Graphics g) {

        // background 
        g.setColor(Color.cyan);
        g.fillRect(0, 0, screenWidth, screenHeight);

        // soil
        g.setColor(Color.orange);
        g.fillRect(0, screenHeight - soilHeight,
                screenWidth, soilHeight);

        // grass
        g.setColor(Color.green);
        g.fillRect(0, screenHeight - groundHeight,
                screenWidth, grassHeight);

        for (int i = 0; i < pipes.size(); i++) {
            pipes.get(i).draw(g);
        }

        bird.draw(g);

        // show the starting screen
        if (started == false) {

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Click to start!", screenWidth / 2 - 120, screenHeight / 2 - 50);

            g.setFont(new Font("Arial", Font.BOLD, 90));
            g.drawString("FlappyBird", screenWidth / 2 - 220, screenHeight / 2 - 150);

        } else { // only show score once the game started

            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("" + score, screenWidth / 2 - 20, 100);

        }

        // show the gameover screen
        if (gameOver == true) {

            // black semi-transparent
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, screenWidth, screenHeight);

            g.setColor(Color.WHITE);
            g.drawString("Game Over!", screenWidth / 2 - 100, screenHeight / 2 - 50);
            g.drawString("Click to restart", screenWidth / 2 - 130, screenHeight / 2);

        }
    }
}
