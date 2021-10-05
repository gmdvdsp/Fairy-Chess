package model;

import java.util.List;

// Represents one of the three planes which makes up the board.
public class Plane {

    public Plane() {
    }

    // METHODS:

    // MODIFIES: this
    // EFFECTS: Updates a plane after a piece has moved by exchanging the old plane with the new one
    //          that inserts a piece at some coordinate.
    public void updatePlane(Piece p, ) {
    }

    // EFFECTS: Returns the piece at chess-notation coordinate (letter, number), else if no piece is there,
    //          return null;
    public Piece getPieceAtCoordinate(int coordinate) {
        return null;
    }

    // EFFECTS: Returns a plane as a list of pieces on that plane, in order from the bottom left square
    //          going right, up to the second row and left, and so on until the top right, with null in the
    //          coordinates that contain no pieces.
    public List<Piece> getPlane() {
        return null;
    }
}

