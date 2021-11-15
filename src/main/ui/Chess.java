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

public class Chess extends JFrame {
    private static final String JSON_STORE = "./data/game.json";
    private final JSonWriter jsonWriter;
    private final JSonReader jsonReader;
    private final Players players;

    private JFrame frame;
    private GamePanel gamePanel;
    private TopPanel topPanel;
    private CapturedPiecePanel capturedPiecePanel;

    public Chess() {
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
        addSaveOnQuitHandler();
    }

    private void initLoadPrompt() {
        // From https://stackoverflow.com/questions/21330682/confirmation-before-press-yes-to-exit-program-in-java
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

    private void addSaveOnQuitHandler() {
        frame.addWindowListener(new WindowAdapter() {
            // From https://stackoverflow.com/questions/21330682/confirmation-before-press-yes-to-exit-program-in-java
            @Override
            public void windowClosing(WindowEvent e) {
                if (!players.getGame().getEndGame()) {
                    int confirmed = JOptionPane.showConfirmDialog(null,
                            "Save?", "Save prompt",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmed == JOptionPane.YES_OPTION) {
                        saveGame();
                    }
                }
            }
        });
    }

    private void saveGame() {
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
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Chess();
    }
}
