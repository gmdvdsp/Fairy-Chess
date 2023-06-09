package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

// Represents a chess game that has a board, the current turn, a list of captured pieces, and whether the game is over.
public class Game implements Writable {
    Board board;
    String currentTurn;
    List<BasePiece> capturedPieces;
    boolean endGame;

    // MODIFIES: this
    // EFFECTS: Makes a new game that has a board with the default size (9, 7), white to play, an empty list of
    // captured pieces.
    public Game() {
        board = new Board(9, 7);
        currentTurn = "white";
        capturedPieces = new ArrayList<>();
        endGame = false;
    }

    // Methods:
    // ===================================================
    // Saving:
    // =========================
    // EFFECTS: Converts a game to a JSONObject with board, turn, and list of capturedPieces.
    @Override
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("board", board.toJSon());
        json.put("currentTurn", currentTurn);
        json.put("capturedPieces", capturedPiecesToJSon());
        return json;
    }

    // EFFECTS: returns pieces in capturedPieces as a JSON array
    private JSONArray capturedPiecesToJSon() {
        JSONArray jsonArray = new JSONArray();
        for (BasePiece p : capturedPieces) {
            jsonArray.put(p.toJSon());
        }
        return jsonArray;
    }

    // Game flow processing:
    // =========================
    // REQUIRES: Exactly one square on the board has from.getX() and from.getY(), and exactly one square on the board
    // has to.getX() and to.getY()
    // MODIFIES: this
    // EFFECTS: Processes a capture, clears from of pieces, and moves the piece on from to to.
    public void processMove(Square from, Square to) {
        BasePiece piece = from.getPieceOnSquare();
        Square newFrom = getBoard().getSquareAt(from.getPosX(), from.getPosY());
        Square newTo = getBoard().getSquareAt(to.getPosX(), to.getPosY());
        processCaptures(from.getPieceOnSquare(), to);
        newTo.setPiece(piece);
        newTo.setIcon(piece.getIcon());
        newFrom.setPiece(null);
        newFrom.setIcon(null);
        flipTurn();
    }

    // (NOTE: Maybe should go inside board.)
    // EFFECTS: Returns true if a square has a piece who's colour matches currentTurn. Returns false if no such piece
    // exists, or that piece has the other colour.
    public boolean controlsSquare(Square from) {
        if (from.getIsEmpty()) {
            return false;
        } else {
            return (from.getPieceOnSquare().getColour().equals(currentTurn));
        }
    }

    // MODIFIES: this
    // EFFECTS: If a move would go to some square that has some piece, add that piece to a list of captured pieces,
    // and if that piece was a king, end the game. Else, do nothing.
    private void processCaptures(BasePiece piece, Square to) {
        BasePiece capturedPiece = to.getPieceOnSquare();
        if (capturedPiece instanceof King) {
            setEndGame(true);
        } else if (capturedPiece != null) {
            capturedPieces.add(capturedPiece);
            addCaptureEvent(piece, capturedPiece, to);
        } else {
            addMoveEvent(piece, to);
        }
    }

    // MODIFIES: this
    // EFFECTS: After a white move, change the current turn to black, and vice versa.
    public void flipTurn() {
        if (currentTurn.equals("white")) {
            currentTurn = "black";
        } else {
            currentTurn = "white";
        }
    }

    // Event Logging:
    // =========================
    // MODIFIES: EventLog
    // EFFECTS: Adds a move event to the single EventLog that has the form "[color] [piece] to (x,y)."
    private void addMoveEvent(BasePiece piece, Square to) {
        EventLog.getInstance().logEvent(new Event(piece.getColour() + " " + piece.getClass().getSimpleName()
                + " to " + "(" + to.getPosX() + "," + to.getPosY() + ")."));
    }

    // MODIFIES: this
    // EFFECTS: Adds a piece into the capturedPiece list, and logs it as an event.
    public void addCapturedPieceFromLoad(BasePiece piece) {
        capturedPieces.add(piece);
        addLoadCaptureEvent(piece);
    }

    // MODIFIES: EventLog
    // EFFECTS: Adds a capture event to the single EventLog that has the form "[color] [piece] captured at (x,y)."
    private void addCaptureEvent(BasePiece capturer, BasePiece piece, Square to) {
        EventLog.getInstance().logEvent(new Event(capturer.getColour() + " "
                + capturer.getClass().getSimpleName() + " takes " + piece.getColour() + " "
                + piece.getClass().getSimpleName() + " at " +  "(" + to.getPosX() + "," + to.getPosY() + ")."));
    }

    // MODIFIES: EventLog
    // EFFECTS: Adds a load captured piece event to the single EventLog that has the form "Captured [color] [piece]
    // loaded."
    private void addLoadCaptureEvent(BasePiece piece) {
        EventLog.getInstance().logEvent(new Event("Captured " + piece.getColour() + " "
                + piece.getClass().getSimpleName() + " " + "loaded."));
    }

    // Move legality checking:
    // =========================
    // Checks checks if move is legal traversal or legal capture.
    public boolean isLegalMove(Square from, Square to) {
        return (isLegalTraversal(from, to) || isLegalCapture(from, to));
    }

    // Traversal to empty squares checking:
    // =========================
    // EFFECTS: Returns true if a piece on from can move to another square, and to is empty. Returns false if no piece
    // exists on from, or the move does not comply with that piece's movement in chess.
    private boolean isLegalTraversal(Square from, Square to) {
        if (to.getIsEmpty()) {
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
                return (isLegalRookMove(from, to) ^ isLegalBishopMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Princess) {
                return (isLegalRookMove(from, to) ^ isLegalKnightMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Dragon) {
                return (isLegalBishopMove(from, to) ^ isLegalKnightMove(from, to));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Pawns:
    // ===========
    // REQUIRES: There is some piece on from.
    // EFFECTS: Returns true if a piece on from can traverse to some square to legally like a pawn.
    private boolean isLegalPawnMove(Square from, Square to) {
        if (from.getPieceOnSquare().getColour().equals("white")) {
            return (isLegalSingleWhitePawnMove(from, to));
        } else {
            return (isLegalSingleBlackPawnMove(from, to));
        }
    }

    // (NOTE: Should refactor to use abs(distance) instead of having two functions for different colours.)
    // EFFECTS: Returns true if a piece tries to move two empty squares forward on its first turn, or tries
    // to move one empty square forward. Returns false otherwise.
    private boolean isLegalSingleWhitePawnMove(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        if (distance.getPosX() == 0 && from.getPosY() == 1 && to.getPosY() == 3) {
            return (board.isCardinalDirectionEmpty(from, to));
        } else {
            return (distance.getPosX() == 0 && distance.getPosY() == 1);
        }
    }

    // EFFECTS: Returns true if a piece tries to move two empty squares down on its first turn, or tries
    // to move one empty square down. Returns false otherwise.
    private boolean isLegalSingleBlackPawnMove(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        if (distance.getPosX() == 0 && from.getPosY() == board.getYmax() - 1 && to.getPosY() == board.getYmax() - 3) {
            return (board.isCardinalDirectionEmpty(from, to));
        } else {
            return (distance.getPosX() == 0 && distance.getPosY() == -1);
        }
    }

    // Kings:
    // ===========
    // EFFECTS: Returns true if a piece tries to move to empty squares exactly one square away. Returns false otherwise.
    public boolean isLegalKingMove(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        return (abs(distance.getPosX()) == 1 && abs(distance.getPosY()) == 1
                || abs(distance.getPosX()) == 0 && abs(distance.getPosY()) == 1
                || abs(distance.getPosX()) == 1 && abs(distance.getPosY()) == 0);
    }

    // Rooks:
    // ===========
    // EFFECTS: Returns true if a piece tries to move to an unblocked, empty square on either the same file or rank.
    // Returns false otherwise.
    private boolean isLegalRookMove(Square from, Square to) {
        if (from.isOnSameFile(to) || from.isOnSameRank(to)) {
            return (board.isCardinalDirectionEmpty(from, to));
        } else {
            return false;
        }
    }

    // Bishops:
    // ===========
    // EFFECTS: Returns true if a piece tries to move to an unblocked, empty square on the same diagonal. Returns false
    // otherwise.
    private boolean isLegalBishopMove(Square from, Square to) {
        if (from.isOnSameDiagonal(to)) {
            return (board.isDiagonalDirectionEmpty(from, to));
        } else {
            return false;
        }
    }

    // Knights:
    // ===========
    // EFFECTS: Returns true if a piece tries to move exactly two squares in any cardinal direction, and then exactly
    // one square orthogonally to that movement. Returns false otherwise.
    private boolean isLegalKnightMove(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        return (abs(distance.getPosX()) == 2 && abs(distance.getPosY()) == 1)
                || (abs(distance.getPosX()) == 1 && abs(distance.getPosY()) == 2);
    }

    // Capture to non-empty squares checking:
    // =========================
    // REQUIRES: There is a piece on both from and to.
    // EFFECTS: Returns true if a piece on from would be able to capture some piece on to.
    private boolean isLegalCapture(Square from, Square to) {
        if (!to.getIsEmpty() && !from.getPieceOnSquare().getColour().equals(to.getPieceOnSquare().getColour())) {
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
                return (isLegalRookMove(from, to) ^ isLegalBishopMove(from, to));
            } else if (from.getPieceOnSquare() instanceof Princess) {
                return (isLegalRookMove(from, to) ^ isLegalKnightMove(from, to));
            } else {
                return (isLegalBishopMove(from, to) ^ isLegalKnightMove(from, to));
            }
        } else {
            return false;
        }
    }

    // Pawns:
    // ===========
    // REQUIRES: There is a piece on from.
    // EFFECTS: Returns true if a piece on from can capture legally as a pawn. Returns false otherwise.
    private boolean isLegalPawnCapture(Square from, Square to) {
        if (from.getPieceOnSquare().getColour().equals("white")) {
            return isLegalWhitePawnCapture(from, to);
        } else {
            return isLegalBlackPawnCapture(from, to);
        }
    }

    // EFFECTS: Returns true if to is exactly one square away left or right, and exactly one square up. Returns false
    // otherwise.
    private boolean isLegalWhitePawnCapture(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        return (abs(distance.getPosX()) == 1 && distance.getPosY() == 1);
    }

    // EFFECTS: Returns true if to is exactly one square away left or right, and exactly one square down. Returns false
    // otherwise.
    private boolean isLegalBlackPawnCapture(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        return (abs(distance.getPosX()) == 1 && distance.getPosY() == -1);
    }

    // Non-simple getters:
    // =========================
    // EFFECTS: Returns all captured pieces in capturedPieces as " cP, ", where c is the first letter of the colour
    // and P is the first letter of the piece. Returns an empty string if no pieces have been captured.
    public String parseCapturedPieces() {
        StringBuilder capturedList = new StringBuilder();
        for (BasePiece piece : capturedPieces) {
            capturedList.append(piece.printColourOneCharacter()).append(piece.printPiece()).append(", ");
        }
        return capturedList.toString();
    }

    // Simple getters:
    // =========================
    public Board getBoard() {
        return board;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public boolean getEndGame() {
        return endGame;
    }

    public List<BasePiece> getCapturedPieces() {
        return capturedPieces;
    }

    // Simple setters:
    // =========================
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
