package ui;

import model.BasePiece;
import model.Players;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CapturedPiecePanel extends JTabbedPane {
    Players players;
    List<BasePiece> localCapturedList = new ArrayList<>();
    JPanel whiteTab;
    JPanel blackTab;

    public CapturedPiecePanel(Players players) {
        this.players = players;

        whiteTab = new JPanel();
        blackTab = new JPanel();

        initTabs();
        initCapturedPiecePanel();
        addTabs();
    }

    private void initTabs() {
        whiteTab.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        whiteTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        whiteTab.setPreferredSize(new Dimension(40,50));
        blackTab.setLayout(new FlowLayout(FlowLayout.LEFT, -3, 0));
        blackTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        blackTab.setPreferredSize(new Dimension(40,70));
    }

    private void addTabs() {
        addTab("White Captured Pieces", null, whiteTab, "See all captured white pieces.");
        addTab("Black Captured Pieces", null, blackTab, "See all captured black pieces.");
    }

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

