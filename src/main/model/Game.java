package model;

import static java.lang.Math.abs;

public class Game {
    public static final int MAX_X_COORDINATE = 8;
    public static final int MAX_Y_COORDINATE = 7;

    public static final int SQUARES_ON_ROW = MAX_X_COORDINATE + 1;
    public static final int SQUARES_ON_COLUMN = MAX_Y_COORDINATE + 1;

    public static final int SECOND_RANK = 1;
    public static final int SEVENTH_RANK = 6;

    Board board;

    public Game() {
        board = new Board();
    }

    // METHODS:
    // IS LEGAL?
    // ========================================================
    // REQUIRES: fromSquare.getPiece() != null;
    // Checks checks if move is legal traversal or legal capture.
    public boolean isLegalMove(Square from, Square to) {
        return (isLegalTraversal(from, to) || isLegalCapture(from, to));
    }

    // CONFORMS WITH DIRECTIONS?
    // ================================================================================================================
    // Traversal is moving to an empty square.
    private boolean isLegalTraversal(Square from, Square to) {
        if (to.getIsEmpty()) {
            if (from.getPieceOnSquare() instanceof Pawn) {
                return (isLegalPawnMove(from, to));
            } else if (from.getPieceOnSquare() instanceof King) {
                return (isLegalKingMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Rook) {
                return (isLegalRookMove(from, to));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // PAWNS:
    // ========================================================
    private boolean isLegalPawnMove(Square from, Square to) {
        if (from.getColourOfPieceOnSquare().equals("white")) {
            return (isLegalSingleWhitePawnMove(from, to));
        } else {
            return (isLegalSingleBlackPawnMove(from, to));
        }
    }

    private boolean isLegalSingleWhitePawnMove(Square from, Square to) {
        if (from.getY() == SECOND_RANK && to.getY() == 3) {
            return (board.isSquareAboveEmpty(from));
        } else {
            return (board.getDistanceToX(from, to) == 0 && board.getDistanceToY(from, to) == 1);
        }
    }

    private boolean isLegalSingleBlackPawnMove(Square from, Square to) {
        if (from.getY() == SEVENTH_RANK && to.getY() == 4) {
            return (board.isSquareBelowEmpty(from));
        } else {
            return (board.getDistanceToX(from, to) == 0 && board.getDistanceToY(from, to) == -1);
        }
    }

    // KINGS:
    // ========================================================
    private boolean isLegalKingMove(Square from, Square to) {
        return (isLegalSingleKingMove(from, to));
    }

    private boolean isLegalSingleKingMove(Square from, Square to) {
        return (abs(board.getDistanceToX(from, to)) == 1 && abs(board.getDistanceToY(from, to)) == 1
                || abs(board.getDistanceToX(from, to)) == 1 && board.getDistanceToY(from, to) == 0
                || (board.getDistanceToX(from, to) == 0 && abs(board.getDistanceToY(from, to)) == 1));
    }

    // ROOK:
    // ========================================================
    // Start at from, to
    // Check if from, from + 1 is valid move
    // Check if from + 1, (from + 1) + 1 is valid move
    // ...
    // Stop at to

    private boolean isLegalRookMove(Square from, Square to) {
        if (board.isOnSameFile(from, to) ^ board.isOnSameRank(from, to)) {
            // do something
            return true;
        } else {
            return false;
        }
    }

    // a move is legal if you can chain a sequence of legal moves to get to that square
    // first, check if move conforms
    // Now we need 2 things: the direction, and the amount of squares to move == A.
    // From direction, call the appropriate SingleMove function A times, incrementing the corresponding position by 1
    // each time.

    private boolean isLegalSingleRookMoveUp(Square from, Square to) {
        return (board.isSquareAboveEmpty(from) && (board.getDistanceToY(from, to) == 1));
    }

    private boolean isLegalSingleRookMoveDown(Square from, Square to) {
        return (board.isSquareBelowEmpty(from) && (board.getDistanceToY(from, to) == -1));
    }

    private boolean isLegalSingleRookMoveRight(Square from, Square to) {
        return (board.isSquareRightEmpty(from) && (board.getDistanceToX(from, to) == 1));
    }

    private boolean isLegalSingleRookMoveLeft(Square from, Square to) {
        return (board.isSquareLeftEmpty(from) && (board.getDistanceToX(from, to) == -1));
    }

    // CONFORMS WITH CAPTURES?
    // ================================================================================================================
    // Checks checks if move is a legal capture.
    private boolean isLegalCapture(Square from, Square to) {
        if (!to.getIsEmpty() && !from.getColourOfPieceOnSquare().equals(to.getColourOfPieceOnSquare())) {
            if (from.getPieceOnSquare() instanceof Pawn) {
                return (isLegalPawnCapture(from, to));
            } else if (from.getPieceOnSquare() instanceof King) {
                return (isLegalKingCapture(from, to));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // PAWNS:
    // ========================================================
    private boolean isLegalPawnCapture(Square from, Square to) {
        if (from.getColourOfPieceOnSquare().equals("white")) {
            return isLegalWhitePawnCapture(from, to);
        } else {
            return isLegalBlackPawnCapture(from, to);
        }
    }

    private boolean isLegalWhitePawnCapture(Square from, Square to) {
        return (abs(board.getDistanceToX(from, to)) == 1 && board.getDistanceToY(from, to) == 1);
    }

    private boolean isLegalBlackPawnCapture(Square from, Square to) {
        return (abs(board.getDistanceToX(from, to)) == 1 && board.getDistanceToY(from, to) == -1);
    }

    // KINGS:
    // ========================================================
    private boolean isLegalKingCapture(Square from, Square to) {
        return (isLegalKingMove(from, to));
    }

    // ========================================================

    public void makeMove(Square fromSquare, Square toSquare) {
        // board.move()
    }

    public Board getBoard() {
        return board;
    }
}
