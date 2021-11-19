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

// Represents the top level frame onto which all the game panels go.
public class Chess extends JFrame {
    private static final String JSON_STORE = "./data/game.json";
    private final JSonWriter jsonWriter;
    private final JSonReader jsonReader;
    private final Players players;

    private JFrame frame;
    private GamePanel gamePanel;
    private TopPanel topPanel;
    private CapturedPiecePanel capturedPiecePanel;

    // MODIFIES: this
    // EFFECTS: Creates a new game scenario; asks the player if they would like to load a previously loaded game, and
    // then creates a a gamePanel, topPanel, and capturedPiecePanel that matches the current game state, regardless
    // of load state. Then, adds a handler to ask the player if they would like to save the game on quit.
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

    // MODIFIES: this
    // EFFECTS: Displays a prompt that asks the player to load when they start the app; if yes is selected, load the
    // game from JSON; if no selected, create a new game instead.
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

    // MODIFIES: this
    // EFFECTS: Creates a new unresizable frame with a title.
    private void initFrame() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(430, 530));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Chess (With Dragons)");
    }

    // MODIFIES: this
    // EFFECTS: Creates UI panels that correspond to the current state of the game.
    private void initPanels() {
        topPanel = new TopPanel(players);
        capturedPiecePanel = new CapturedPiecePanel(players);
        gamePanel = new GamePanel(players, topPanel, capturedPiecePanel);
    }

    // EFFECTS: Displays a prompt that asks the player to save when they close the app; if yes is selected, save the
    // game to JSON; if no selected, simply close.
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

    // EFFECTS: Writes the game to the JSON entry, throws a FileNotFoundException if writing was impossible.
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(players.getGame());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Loads the game from the JSON entry, throws a FileNotFoundException if reading was impossible.
    private void loadGame() {
        try {
            players.setGame(jsonReader.read());
            System.out.println("Game loaded.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Sets the UI to have a cross-platform feel to stay consistent on all platforms before making a new Chess
    // app. Throws an exception if this look and feel path could not be found or instantiated.
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
                | InstantiationException e) {
            System.out.println("Button styles not found!");
        }
        new Chess();
    }
}
