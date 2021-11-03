package ui;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
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
    private Icon pieceToMove;
    Square proposedFrom;
    private final Color black = new Color(194,127,74);
    private final Color white = new Color(216,178,134);


    public Chess() {
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
        game = new Game();
        game.getBoard().defaultBoard();

        frame = new JFrame();
        panel = new JPanel();

        frame.setPreferredSize(new Dimension(470, 450));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        SwingUtilities.updateComponentTreeUI(frame);

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
                    square.setOriginalColor(black);
                } else {
                    square.setBackground(white);
                    square.setOriginalColor(white);
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
            if (selected == proposedFrom) {
                selected.setSelected(false);
            }
            if (proposedFrom == null) {
                processProposedFrom(selected);
            } else {
                processProposedTo(selected);
            }
        }
    }

    private void processProposedFrom(Square selected) {
        if (!selected.getIsEmpty()) {
            proposedFrom = selected;
            pieceToMove = proposedFrom.getPieceOnSquare().getIcon();

            proposedFrom.setSelected(true);
            showRange(proposedFrom);
        } else {
            selected.setSelected(false);
        }
    }

    private void processProposedTo(Square selected) {
        if (game.isLegalMove(proposedFrom, selected)) {
            game.processMove(proposedFrom, selected);
            proposedFrom.setSelected(false);
            selected.setSelected(false);
        } else {
            processProposedFrom(selected);
        }
    }

    private void showRange(Square proposedFrom) {
        for (Square square : game.getBoard().getSquareList()) {
            if (game.isLegalMove(proposedFrom, square)) {
                square.setBackground(Color.GREEN);
            }
        }
    }

    private void wipe() {
        for (Square square : game.getBoard().getSquareList()) {
            square.setBackground(square.getOriginalColor());
        }
    }
}
