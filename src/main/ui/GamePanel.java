package ui;

import model.Game;
import model.Players;
import model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private final Players players;
    private final Game game;
    private final TopPanel topPanel;
    private final CapturedPiecePanel capturedPiecePanel;
    Square proposedFrom;
    Square proposedTo;
    private final Color black = new Color(194,127,74);
    private final Color white = new Color(216,178,134);
    private final Color moveRange = new Color(35, 168, 0);
    private final Color captureRange = new Color(171, 0, 0);

    public GamePanel(Players players, TopPanel topPanel, CapturedPiecePanel capturedPiecePanel) {
        this.players = players;
        this.game = players.getGame();
        this.topPanel = topPanel;
        this.capturedPiecePanel = capturedPiecePanel;
        setLayout(new GridLayout(8,10,0,0));
        initUI();
    }

    private void initUI() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x <= 9; x++) {
                Square square = game.getBoard().getSquareAt(x,y);
                if ((x + y) % 2 == 0) {
                    square.setBackground(white);
                    square.setOriginalColor(white);
                } else {
                    square.setBackground(black);
                    square.setOriginalColor(black);
                }
                if (square.getPieceOnSquare() != null) {
                    square.setIcon(square.getPieceOnSquare().getIcon());
                }
                square.addActionListener(this);
                square.setFocusPainted(false);
                add(square);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wipeRanges();

        if (e.getSource() instanceof Square) {
            Square selected = (Square) e.getSource();
            if (proposedFrom != null) {
                proposedTo = selected;
                processMove();
            } else {
                proposedFrom = selected;
                showRange(selected);
            }
        }
    }

    private void processMove() {
        if (players.proposeMove(proposedFrom, proposedTo)) {
            players.makeMove(proposedFrom, proposedTo);
            if (players.getGame().getEndGame()) {
                endGame();
            }
            topPanel.updateTurnPanelText();
            capturedPiecePanel.updateCapturedPiecePanel();
        }
        proposedFrom = null;
        proposedTo = null;
    }

    private void showRange(Square selected) {
        boolean isLegalMove;
        boolean isEnemy = false;
        if (!selected.getIsEmpty()) {
            for (Square square : game.getBoard().getSquareList()) {
                isLegalMove = players.proposeMove(selected, square);
                if (!square.getIsEmpty()) {
                    isEnemy = (!square.getPieceOnSquare().getColour().equals(selected.getPieceOnSquare().getColour()));
                }
                if (isLegalMove) {
                    if (square.getIsEmpty()) {
                        square.setBackground(moveRange);
                    } else if (isEnemy) {
                        square.setBackground(captureRange);
                    }
                }
                isEnemy = false;
            }
        }
    }

    private void wipeRanges() {
        for (Square square : game.getBoard().getSquareList()) {
            square.setBackground(square.getOriginalColor());
        }
    }

    private void endGame() {
        for (Square square : game.getBoard().getSquareList()) {
            square.removeActionListener(this);
        }
        topPanel.displayGameOver();
    }
}
