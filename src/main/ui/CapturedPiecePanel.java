package ui;

import model.BasePiece;
import model.Players;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents the two lower tabs that display black and white captured pieces respectively.
public class CapturedPiecePanel extends JTabbedPane {
    Players players;
    List<BasePiece> localCapturedList = new ArrayList<>();
    JPanel whiteTab;
    JPanel blackTab;

    // EFFECTS: Creates a new captured piece panel with a tab for displaying black and white captured pieces, and
    // captured pieces on these tabs equal to the current status of the player's game.
    public CapturedPiecePanel(Players players) {
        this.players = players;

        whiteTab = new JPanel();
        blackTab = new JPanel();

        initTabs();
        initCapturedPiecePanel();
        addTabs();
    }

    // MODIFIES: this
    // EFFECTS: Creates two tabs that hold the correct size to fit eighteen captured pieces from each side.
    private void initTabs() {
        whiteTab.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        whiteTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        whiteTab.setPreferredSize(new Dimension(40,70));
        blackTab.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        blackTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        blackTab.setPreferredSize(new Dimension(40,70));
    }

    // MODIFIES: this
    // EFFECTS: Labels two tabs with their respective captured piece information and adds them this.
    private void addTabs() {
        addTab("White Captured Pieces", null, whiteTab, "See all captured white pieces.");
        addTab("Black Captured Pieces", null, blackTab, "See all captured black pieces.");
    }

    // MODIFIES: this
    // EFFECTS: Adds the current player's game's captured pieces onto the two captured piece tabs.
    private void initCapturedPiecePanel() {
        for (BasePiece piece : players.getGame().getCapturedPieces()) {
            ImageIcon imageIcon = new ImageIcon(piece.getIcon()
                    .getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            if (piece.getColour().equals("white")) {
                whiteTab.add(new JLabel(imageIcon));
            } else {
                blackTab.add(new JLabel(imageIcon));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds the newest captured piece from a current turn to the correct tab, only if a piece was actually
    // captured that turn.
    public void updateCapturedPiecePanel() {
        List<BasePiece> capturedList = players.getGame().getCapturedPieces();
        BasePiece piece;
        if (capturedList.size() > localCapturedList.size()) {
            piece = players.getGame().getCapturedPieces().get(capturedList.size() - 1);
            ImageIcon imageIcon = new ImageIcon(piece.getIcon()
                    .getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            if (piece.getColour().equals("white")) {
                whiteTab.add(new JLabel(imageIcon));
            } else {
                blackTab.add(new JLabel(imageIcon));
            }
            localCapturedList = new ArrayList<>(capturedList);
        }
    }
}

