package model;

// Represents a queen that has some colour.
public class Queen extends BasePiece {
    public Queen(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("Q");
    }
}
