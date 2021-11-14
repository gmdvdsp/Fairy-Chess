package ui;

import model.Players;

import javax.swing.*;

public class TopPanel extends JPanel {
    Players players;
    JLabel currentTurn;

    public TopPanel(Players players) {
        this.players = players;
        initTurnPanel();
    }

    public void initTurnPanel() {
        this.currentTurn = new JLabel("It's " + players.getGame().getCurrentTurn() + "'s turn.");
        add(currentTurn);
    }

    public void updateTurnPanelText() {
        currentTurn.setText("It's " + players.getGame().getCurrentTurn() + "'s turn.");
    }
}
