package ui;

import model.Players;

import javax.swing.*;
import java.awt.*;

public class Chess extends JFrame {
    private JFrame frame;
    private GamePanel gamePanel;
    private TurnPanel turnPanel;
    private CapturedPiecePanel capturedPiecePanel;
    private Players players;

    public Chess() {
        players = new Players();
        players.getGame().getBoard().defaultBoard();

        frame = new JFrame();
        turnPanel = new TurnPanel(players);
        capturedPiecePanel = new CapturedPiecePanel(players);
        gamePanel = new GamePanel(players, turnPanel, capturedPiecePanel);

        frame.setPreferredSize(new Dimension(490, 530));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.add(gamePanel);
        frame.add(turnPanel, BorderLayout.NORTH);
        frame.add(capturedPiecePanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Chess();
    }
}
