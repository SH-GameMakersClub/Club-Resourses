package tictactoe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TicTacToe {

    // the font of the buttons
    public static Font font = new Font("Arial", Font.BOLD, 120);

    // the players' symbols
    public static String O = "O";
    public static String X = "X";
    
    // keeping track of the current player
    public static String currentPlayer = O;

    /**
     * The main class that runs the game.
     * 
     * @param args 
     */
    public static void main(String[] args) {

        // the frame we're going to play with
        JFrame jframe = new JFrame();

        // title of the frame
        jframe.setTitle("Tic Tac Toe");
        // stop the program when the frame is closed
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // we dont want the player to mess with the screen size
        jframe.setResizable(false);

        // 600 x 600 frame to play the game
        Dimension size = new Dimension(600, 600);
        jframe.setPreferredSize(size);

        // create a 3 by 3 grid
        GridLayout grid = new GridLayout(3, 3);
        jframe.setLayout(grid);

        // what makes the button interact with user
        Clicker clicker = new Clicker();

        // add the buttons 9 times
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton();
            btn.setFont(font);
            btn.addActionListener(clicker);
            jframe.add(btn);
        }

        // pack the frame so it looks nice
        jframe.pack();
        // show the frame
        jframe.setVisible(true);

    }

}
