package ui;

import model.Players;

import javax.swing.*;

public class TopPanel extends JPanel {
    Players players;
    JLabel panelText;

    public TopPanel(Players players) {
        this.players = players;
        initTurnPanel();
    }

    private void initTurnPanel() {
        this.panelText = new JLabel("It's " + players.getGame().getCurrentTurn() + "'s turn.");
        add(panelText);
    }

    public void updateTurnPanelText() {
        panelText.setText("It's " + players.getGame().getCurrentTurn() + "'s turn.");
    }

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
