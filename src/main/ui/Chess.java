package ui;

import model.Players;

import javax.swing.*;
import java.awt.*;

public class Chess extends JFrame {
    private JFrame frame;
    private GamePanel gamePanel;
    private TurnPanel turnPanel;
    private Players players;

    public Chess() {
        players = new Players();
        players.getGame().getBoard().defaultBoard();

        frame = new JFrame();
        turnPanel = new TurnPanel(players);
        gamePanel = new GamePanel(players, turnPanel);

        frame.setPreferredSize(new Dimension(500, 450));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.add(gamePanel);
        frame.add(turnPanel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Chess();
    }
}
