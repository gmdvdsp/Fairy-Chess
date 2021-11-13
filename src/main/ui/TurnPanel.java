package ui;

import model.Players;

import javax.swing.*;

public class TurnPanel extends JPanel {
    Players players;
    JLabel currentTurn;

    public TurnPanel(Players players) {
        this.players = players;
        this.currentTurn = new JLabel("It's " + players.getGame().getCurrentTurn() + "'s turn.");
        add(currentTurn);
    }

    public void updateTurnPanelText() {
        currentTurn.setText("It's " + players.getGame().getCurrentTurn() + "'s turn.");
    }
}
