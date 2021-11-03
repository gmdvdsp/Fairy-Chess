package ui;

import com.sun.org.apache.bcel.internal.generic.Select;
import model.Game;
import model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private Game game;
    Square proposedFrom;
    Square proposedTo;
    private final Color black = new Color(194,127,74);
    private final Color white = new Color(216,178,134);


    public Chess() {
        game = new Game();
        game.getBoard().defaultBoard();

        frame = new JFrame();
        panel = new JPanel();

        frame.setPreferredSize(new Dimension(500, 450));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        panel.setLayout(new GridLayout(8,10,0,0));

        initUI();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void initUI() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 9; x >= 0; x--) {
                Square square = game.getBoard().getSquareAt(x,y);
                if ((x + y) % 2 == 0) {
                    square.setBackground(black);
                } else {
                    square.setBackground(white);
                }
                if (!(square.getIsEmpty())) {
                    square.setIcon(square.getPieceOnSquare().getIcon());
                }
                square.addActionListener(this);
                panel.add(square);
            }
        }
    }

    public static void main(String[] args) {
        //new ChessApp();
        new Chess();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wipe();

        if (e.getSource() instanceof Square) {
            Square selected = (Square) e.getSource();
            if (proposedFrom != null) {
                processProposedTo(selected);
            } else {
                processProposedFrom(selected);
            }
        }
    }

    private void processProposedFrom(Square selected) {
        if (!selected.getIsEmpty()) {
            proposedFrom = selected;
            selected.setSelected(true);
            showRange(selected);
        }
    }

    private void processProposedTo(Square selected) {
        if (game.isLegalMove(proposedFrom, selected)) {
            game.processMove(proposedFrom, selected);
            proposedTo = selected;
            redraw();
        } else {
            processProposedFrom(selected);
        }
    }

    private void showRange(Square selected) {
        for (Square square : game.getBoard().getSquareList()) {
            if (game.isLegalMove(selected, square)) {
                square.setSelected(true);
            }
        }
    }

    private void wipe() {
        for (Square square : game.getBoard().getSquareList()) {
            square.setSelected(false);
        }
    }

    private void redraw() {
        Square newTo = game.getBoard().getSquareAt(proposedTo.getPosX(), proposedTo.getPosY());
        newTo.setIcon(newTo.getPieceOnSquare().getIcon());
        panel.revalidate();
        panel.repaint();
    }
}
