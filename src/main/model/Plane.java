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
    public void updatePlane(Piece p, Integer coordinate) {
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

    // EFFECTS: Returns the level of the plane as an integer, (bottom = 1, mid = 2, top = 3).
    public Integer getLevel() {
        return 0;
    }

    // EFFECTS: Sets a plane to have a level.
    public void setLevel(int level) {
    }
}

