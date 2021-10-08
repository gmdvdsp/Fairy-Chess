package model;

import java.util.ArrayList;
import java.util.List;

// Represents the board that has (0,0) -> (0,WIDTH) in the x-axis, and (0,0) -> (0, HEIGHT) in the y-axis.
public class Board {
    public static final int MAX_X_COORDINATE = 8;
    public static final int MAX_Y_COORDINATE = 7;

    public static final int SQUARES_ON_ROW = MAX_X_COORDINATE + 1;
    public static final int SQUARES_ON_COLUMN = MAX_Y_COORDINATE + 1;

    private List<BasePiece> boardState;

    public Board() {
        boardState = new ArrayList<BasePiece>();
    }

    // METHODS:

    // MODIFIES: this
    // EFFECTS: Updates a board after a piece has moved by exchanging the old plane with the new one
    //          that inserts a piece at some coordinate. Note the square is always empty.
    public void addToBoard(BasePiece p) {
        boardState.add(p);
    }

    // REQUIRES: BasePiece is on the board
    // MODIFIES: this
    // EFFECTS: Tells a piece to die, and then remove that piece from the board.
    public void removeFromBoard(BasePiece p) {
        boardState.remove(p);
        p.die();
    }

    // EFFECTS: Converts a piece's x and y values to it's corresponding index position, starting from (0,0) as 0,
    //          then (0,1) as WIDTH + 1, (0,2) as WIDTH + 1, and so on.
    private int convertPiecePositionToIndex(int x, int y) {
        return (x * (MAX_X_COORDINATE + 1)) + y;
    }

    // EFFECTS:
    private int convertIndexToPieceX(int i) {
        return (i - (i / MAX_X_COORDINATE + 1));
    }

    // EFFECTS:
    private int convertIndexToPieceY(int i) {
        return (i / MAX_X_COORDINATE + 1);
    }

    // EFFECTS: Returns the piece at chess-notation coordinate (number, number), else if no piece is there,
    //          return null;
    public BasePiece getPieceAtCoordinate(int x, int y) {
        BasePiece foundPiece = null;
        for (BasePiece piece : boardState) {
            if (piece.getX() == x && piece.getY() == y) {
                foundPiece = piece;
            }
        }
        return foundPiece;
    }

    // EFFECTS: Returns a board as a list of pieces on that board, in order from the bottom left square
    //          going right, up to the second row and left, and so on until the top right.
    public List<BasePiece> getBoard() {
        return boardState;
    }

    // EFFECTS: Returns the amount of pieces on a board.
    public Integer countBoard() {
        int count = 0;
        for (BasePiece piece : boardState) {
            count++;
        }
        return count;
    }

    // EFFECTS: Returns true if a coordinate has no piece on it.
    public boolean isEmpty(int x, int y) {
        boolean isEmpty = true;
        for (BasePiece piece : boardState) {
            if (((piece.getX() == x) && (piece.getY() == y)) || isEmpty == false) {
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    // EFFECTS: Returns true if a coordinate (x, y) is within the board.
    public boolean isMoveOnBoard(int x, int y) {
        return ((0 <= x) && (x <= MAX_X_COORDINATE) && (0 <= y) && (y <= MAX_X_COORDINATE));
    }

    // REQUIRES: Some BasePiece has coordinates (x, y).
    // EFFECTS: Get the colour of the piece at
    public String getColourOfPieceAt(int x, int y) {
        String colour = "";
        boolean stopSearching = false;
        for (BasePiece piece : boardState) {
            if (((piece.getX() == x) && (piece.getY() == y)) && !stopSearching) {
                colour = piece.getColour();
                stopSearching = true;
            }
        }
        return colour;
    }
}

