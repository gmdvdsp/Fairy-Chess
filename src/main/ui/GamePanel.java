package ui;

import model.Game;
import model.Players;
import model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Players players;
    private Game game;
    private TurnPanel turnPanel;
    Square proposedFrom;
    Square proposedTo;
    private final Color black = new Color(194,127,74);
    private final Color white = new Color(216,178,134);
    private final Color moveRange = new Color(35, 168, 0);
    private final Color captureRange = new Color(171, 0, 0);

    public GamePanel(Players players, TurnPanel turnPanel) {
        this.players = players;
        this.game = players.getGame();
        this.turnPanel = turnPanel;
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
                square.addActionListener(this);
                add(square);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wipe();

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

    public void processMove() {
        if (players.proposeMove(proposedFrom, proposedTo)) {
            players.makeMove(proposedFrom, proposedTo);
            turnPanel.updateTurnPanelText();

        }
        proposedFrom = null;
        proposedTo = null;
    }

    private void showRange(Square selected) {
        boolean isLegalMove;
        boolean isEnemy = false;
        if (!selected.getIsEmpty()) {
            for (Square square : game.getBoard().getSquareList()) {
                isLegalMove = game.isLegalMove(selected, square);
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

    private void wipe() {
        for (Square square : game.getBoard().getSquareList()) {
            square.setBackground(square.getOriginalColor());
        }
    }
}
