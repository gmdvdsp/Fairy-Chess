package ui;

import model.Players;
import persistence.JSonReader;
import persistence.JSonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Chess extends JFrame {
    private static final String JSON_STORE = "./data/game.json";
    private JSonWriter jsonWriter;
    private JSonReader jsonReader;
    private Scanner input;
    private JFrame frame;
    private GamePanel gamePanel;
    private TopPanel topPanel;
    private CapturedPiecePanel capturedPiecePanel;
    private Players players;

    public Chess() {
        input = new Scanner(System.in);
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
        players = new Players();
        initLoadPrompt();

        initFrame();
        initPanels();

        frame.add(gamePanel);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(capturedPiecePanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        initPersistenceHandling();
    }

    private void initLoadPrompt() {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Load the saved game?", "Load prompt",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            loadGame();
        } else {
            players.getGame().getBoard().defaultBoard();
        }
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(430, 530));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initPanels() {
        topPanel = new TopPanel(players);
        capturedPiecePanel = new CapturedPiecePanel(players);
        gamePanel = new GamePanel(players, topPanel, capturedPiecePanel);
    }

    private void initPersistenceHandling() {
        frame.addWindowListener(new WindowAdapter() {
            // From https://stackoverflow.com/questions/21330682/confirmation-before-press-yes-to-exit-program-in-java
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Save?", "Save prompt",
                        JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    saveGame();
                }
            }
        });
    }

    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(players.getGame());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadGame() {
        try {
            players.setGame(jsonReader.read());
            System.out.println("Game loaded.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new Chess();
    }
}
