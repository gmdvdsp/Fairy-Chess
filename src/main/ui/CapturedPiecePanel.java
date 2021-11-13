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
        addTabs();
    }

    public void initTabs() {
        whiteTab.setLayout(new FlowLayout());
        whiteTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        blackTab.setLayout(new FlowLayout());
        blackTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    public void addTabs() {
        addTab("White Captured Pieces", null, whiteTab, "See all captured white pieces.");
        addTab("Black Captured Pieces", null, blackTab, "See all captured black pieces.");
    }

    public void updateCapturedPiecePanel() {
    }
}

