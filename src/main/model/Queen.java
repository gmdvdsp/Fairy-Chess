package model;

public class Queen implements Piece {

    public Queen() {
    }

    // METHODS:

    public Plane move(int coordinate) {
        return null;
    }

    public boolean checkValidMove(int coordinate) {
        return false;
    }

    public Plane capture() {
        return null;
    }

    public boolean isSelected() {
        return false;
    }

    @Override
    public String getPieceColour() {
        return null;
    }
}
