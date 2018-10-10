package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * The thing that allows the button to do stuff. We want the button to display
 * either O or X.
 *
 * @author Juhyung Kim
 */
public class Clicker implements ActionListener {

    /**
     * Fire when the button is clicked. We want the button to display either O
     * or X.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // get the current button we're working with
        JButton button = (JButton) e.getSource();

        // set the text as currentPlayer (either O or X depends on the turn)
        button.setText(TicTacToe.currentPlayer);

        // if the current player is O
        if (TicTacToe.currentPlayer.equals(TicTacToe.O)) {

            // now the current player will be X
            TicTacToe.currentPlayer = TicTacToe.X;

        } else { // if the current player is not O (is X)

            // now the current player will be O
            TicTacToe.currentPlayer = TicTacToe.O;

        }
    }
}
