package ui;

import model.Game;
import model.Players;
import model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents the graphical entity that displays the board and all pieces on it.
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

    // MODIFIES: this
    // EFFECTS: Makes a new game panel that has access to it's corresponding captured and turn panels, with board
    // state equal to the player's current game.
    public GamePanel(Players players, TopPanel topPanel, CapturedPiecePanel capturedPiecePanel) {
        this.players = players;
        this.game = players.getGame();
        this.topPanel = topPanel;
        this.capturedPiecePanel = capturedPiecePanel;
        setLayout(new GridLayout(8,10,0,0));
        initUI();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new graphical representation of a checkered board using the current state of every square in
    // the player's game with the correct piece. Then adds a listener to each square and adds this square to this.
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
    // MODIFIES: this
    // EFFECTS: When a square is clicked, remove the graphical representation of the last piece's range, and sets that
    // square as the "selected" square and shows ranges of the piece on that square, if a square was not already
    // selected, or proposes a move from a previously selected square to this square if a square was already selected.
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

    // MODIFIES: this
    // EFFECTS: Makes a move if it is legal to move from a proposed square to a new square. Then, checks if the game
    // is over, else update the corresponding turn and captured panels. Finally, unselects all squares.
    private void processMove() {
        if (players.proposeMove(proposedFrom, proposedTo)) {
            players.makeMove(proposedFrom, proposedTo);
            if (players.getGame().getEndGame()) {
                endGame();
            } else {
                topPanel.updateTurnPanelText();
                capturedPiecePanel.updateCapturedPiecePanel();
            }
        }
        proposedFrom = null;
        proposedTo = null;
    }

    // MODIFIES: this
    // EFFECTS: If a player controls a square, shows all legal moves that the piece on that square can make; moves in
    // green and captures in red.
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

    // MODIFIES: this
    // EFFECTS: Removes the graphical representation of all captured pieces' ranges.
    private void wipeRanges() {
        for (Square square : game.getBoard().getSquareList()) {
            square.setBackground(square.getOriginalColor());
        }
    }

    // MODIFIES: this
    // EFFECTS: Makes every square un-interactable and update the turn panel to display who won.
    private void endGame() {
        for (Square square : game.getBoard().getSquareList()) {
            square.removeActionListener(this);
        }
        topPanel.displayGameOver();
    }
}
