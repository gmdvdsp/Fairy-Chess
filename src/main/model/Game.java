package model;

import java.util.Objects;

import static java.lang.Math.abs;

public class Game {
    public static final int MAX_X_COORDINATE = 8;
    public static final int MAX_Y_COORDINATE = 7;

    public static final int SQUARES_ON_ROW = MAX_X_COORDINATE + 1;
    public static final int SQUARES_ON_COLUMN = MAX_Y_COORDINATE + 1;

    Board board;

    public Game() {
        board = new Board();
    }

    // METHODS:
    // IS LEGAL?
    // ========================================================
    // Checks checks if move is legal traversal or legal capture.
    public boolean isLegalMove(Square fromSquare, Square toSquare) {
        return (isLegalTraversal(fromSquare, toSquare) || isLegalCapture(fromSquare, toSquare));
    }

    // CONFORMS WITH DIRECTIONS?
    // ========================================================
    private boolean isLegalTraversal(Square fromSquare, Square toSquare) {
        if (fromSquare.getPiece() instanceof Pawn) {
            return (isLegalPawnMove(fromSquare, toSquare));
        } else {
            return false;
        }
    }

    private boolean isLegalPawnMove(Square fromSquare, Square toSquare) {
        return (isLegalSinglePawnMove(fromSquare, toSquare));
    }

    // SIMPLIFY!
    private boolean isLegalSinglePawnMove(Square fromSquare, Square toSquare) {
        Square dummySquare;
        dummySquare = new Square(0, 0);
        dummySquare.setY(fromSquare.getY() - 1);
        if (fromSquare.getY() == 1) {
            return ((toSquare.getIsEmpty() && dummySquare.getIsEmpty() && board.getDistanceToX(fromSquare, toSquare) == 0 && board.getDistanceToY(fromSquare, toSquare) == 2)
                   || (toSquare.getIsEmpty() && (board.getDistanceToX(fromSquare, toSquare) == 0 && board.getDistanceToY(fromSquare, toSquare) == 1)));
        } else {
            return (toSquare.getIsEmpty() && board.getDistanceToX(fromSquare, toSquare) == 0 && board.getDistanceToY(fromSquare, toSquare) == 1);
        }
    }

    // CONFORMS WITH CAPTURES?
    // ========================================================
    // Checks checks if move is a legal capture.
    private boolean isLegalCapture(Square fromSquare, Square toSquare) {
        if (fromSquare.getPiece() instanceof Pawn) {
            return (isLegalPawnCapture(fromSquare, toSquare));
        } else {
            return false;
        }
    }

    // Check if a pawn can legally capture.
    private boolean isLegalPawnCapture(Square fromSquare, Square toSquare) {
        boolean enemy = (!Objects.equals(toSquare.getPiece().getColour(), fromSquare.getPiece().getColour()));
        return (!toSquare.getIsEmpty() && enemy && (board.getDistanceToX(fromSquare, toSquare) == abs(1) && board.getDistanceToY(fromSquare, toSquare) == 1));
    }

    public void makeMove(Square fromSquare, Square toSquare) {
        // board.move()
    }

    public Board getBoard() {
        return board;
    }
}
