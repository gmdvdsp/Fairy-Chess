package ui;

import model.Players;

import javax.swing.*;

// Represents a panel that displays the current turn, unless the game is over, in which it displays who won.
public class TopPanel extends JPanel {
    Players players;
    JLabel panelText;

    // MODIFIES: this
    // EFFECTS: Makes a new panel that display white's turn.
    public TopPanel(Players players) {
        this.players = players;
        this.panelText = new JLabel();
        updateTurnPanelText();
        add(panelText);
    }

    // MODIFIES: this
    // EFFECTS: Displays the current player's turn on this .
    public void updateTurnPanelText() {
        panelText.setText("It's " + players.getGame().getCurrentTurn() + "'s turn.");
    }

    // MODIFIES: this
    // EFFECTS: Displays the victor on this.
    public void displayGameOver() {
        String winner;
        if (players.getGame().getCurrentTurn().equals("white")) {
            winner = "black";
        } else {
            winner = "white";
        }
        panelText.setText("The winner is " + winner + "!");
    }
}
