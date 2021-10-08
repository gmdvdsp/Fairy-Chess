package model;

import java.util.List;

// Represents the board.
public class Board {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 7;

    public Board() {
    }

    // METHODS:

    // MODIFIES: this
    // EFFECTS: Updates a plane after a piece has moved by exchanging the old plane with the new one
    //          that inserts a piece at some coordinate.
    public void addToBoard(Piece p) {
    }

    // MODIFIES: this
    // EFFECTS: Tells a piece to die, and then remove that piece from the board.
    public void removeFromBoard(Piece p) {
    }

    // EFFECTS: Returns the piece at chess-notation coordinate (number, number), else if no piece is there,
    //          return null;
    public Piece getPieceAtCoordinate(int x, int y) {
        return null;
    }

    // EFFECTS: Returns a plane as a list of pieces on that plane, in order from the bottom left square
    //          going right, up to the second row and left, and so on until the top right.
    public List<Piece> getPlane() {
        return null;
    }

    // EFFECTS: Returns the amount of pieces on a plane.
    public Integer countPlane() {
        return 0;
    }

    // EFFECTS: Returns true if a coordinate has no piece on it.
    public boolean isEmpty(int x, int y) {
        return false;
    }

    // EFFECTS: Returns true if a coordinate (x, y) is within the board.
    public boolean isMoveOnBoard(int x, int y) {
        return false;
    }

    // REQUIRES: (x, y) is non-empty.
    // EFFECTS: Get the colour of the piece at
    public String getColourOfPieceAt(int x, int y) {
        return "";
    }
}

