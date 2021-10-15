package model;

// Represents a knight that has some colour.
public class Knight extends BasePiece {
    public Knight(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("N");
    }
}
