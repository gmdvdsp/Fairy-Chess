package model;

import static java.lang.Math.abs;

// Represents a chess game that has a board and knows how pieces move.
public class Game {

    Board board;
    boolean endGame;

    public Game() {
        board = new Board();
        endGame = false;
    }

    // METHODS:

    public boolean controlsSquare(Square from, String colour) {
        if (from.getIsEmpty()) {
            return false;
        } else if (from.getColourOfPieceOnSquare().equals(colour)) {
            return true;
        } else {
            return false;
        }
    }

    // IS LEGAL?
    // ================================================================================================================
    // REQUIRES: fromSquare.getPiece() != null;
    // Checks checks if move is legal traversal or legal capture.
    public boolean isLegalMove(Square from, Square to) {
        return (isLegalCapture(from, to) || isLegalTraversal(from, to));
    }

    public void makeMove(Square from, Square to) {
        to.setPiece(from.getPieceOnSquare());
        from.setPiece(null);
        board.replaceSquare(from);
        board.replaceSquare(to);
    }

    // CONFORMS WITH DIRECTIONS?
    // ================================================================================================================
    // Traversal is moving to an empty square.
    private boolean isLegalTraversal(Square from, Square to) {
        if (to.getIsEmpty()) {
            // BasePiece.isLegalTraversal(from, to)
            if (from.getPieceOnSquare() instanceof Pawn) {
                return (isLegalPawnMove(from, to));
            } else if (from.getPieceOnSquare() instanceof King) {
                return (isLegalKingMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Rook) {
                return (isLegalRookMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Bishop) {
                return (isLegalBishopMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Knight) {
                return (isLegalKnightMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Queen) {
                return (isLegalRookMove(from, to) || isLegalBishopMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Princess) {
                return (isLegalRookMove(from, to) || isLegalKnightMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Dragon) {
                return (isLegalBishopMove(from, to) || isLegalKnightMove(from, to));
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
            return (board.isCardinalDirectionEmpty(from, to, board.getDistanceBetween(from, to)));
        } else {
            return (board.getDistanceToX(from, to) == 0 && board.getDistanceToY(from, to) == 1);
        }
    }

    private boolean isLegalSingleBlackPawnMove(Square from, Square to) {
        if (from.getY() == SEVENTH_RANK && to.getY() == 4) {
            return (board.isCardinalDirectionEmpty(from, to, board.getDistanceBetween(from, to)));
        } else {
            return (board.getDistanceToX(from, to) == 0 && board.getDistanceToY(from, to) == -1);
        }
    }

    // KINGS:
    // ========================================================
    public boolean isLegalKingMove(Square from, Square to) {
        Square distance = board.getDistanceBetween(from, to);
        return (abs(distance.getX()) == 1 && abs(distance.getY()) == 1
                || abs(distance.getX()) == 0 && abs(distance.getY()) == 1
                || abs(distance.getX()) == 1 && abs(distance.getY()) == 0);
    }

    // ROOK:
    // ========================================================
    private boolean isLegalRookMove(Square from, Square to) {
        if (board.isOnSameFile(from, to) || board.isOnSameRank(from, to)) {
            return (board.isCardinalDirectionEmpty(from, to, board.getDistanceBetween(from, to)));
        } else {
            return false;
        }
    }

    // BISHOP:
    // ========================================================
    private boolean isLegalBishopMove(Square from, Square to) {
        if (board.isOnSameDiagonal(from, to)) {
            return (board.isDiagonalDirectionEmpty(from, to, board.getDistanceBetween(from, to)));
        } else {
            return false;
        }
    }

    // KNIGHTS:
    // ========================================================
    private boolean isLegalKnightMove(Square from, Square to) {
        Square distance = board.getDistanceBetween(from, to);
        return (abs(distance.getX()) == 2 && abs(distance.getY()) == 1)
                || (abs(distance.getX()) == 1 && abs(distance.getY()) == 2);
    }

    // CONFORMS WITH CAPTURES?
    // ================================================================================================================
    // Checks if move is a legal capture.
    private boolean isLegalCapture(Square from, Square to) {
        if (!to.getIsEmpty() && !from.getColourOfPieceOnSquare().equals(to.getColourOfPieceOnSquare())) {
            if (from.getPieceOnSquare() instanceof Pawn) {
                return (isLegalPawnCapture(from, to));
            } else if (from.getPieceOnSquare() instanceof King) {
                return (isLegalKingMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Rook) {
                return (isLegalRookMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Bishop) {
                return (isLegalBishopMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Knight) {
                return (isLegalKnightMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Queen) {
                return (isLegalRookMove(from, to) || isLegalBishopMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Princess) {
                return (isLegalRookMove(from, to) || isLegalKnightMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Dragon) {
                return (isLegalBishopMove(from, to) || isLegalKnightMove(from, to));
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

    public Board getBoard() {
        return board;
    }

    public boolean getEndGame() {
        return endGame;
    }
}
