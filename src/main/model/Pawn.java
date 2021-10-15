package model;

// Represents a pawn that has some colour.
public class Pawn extends BasePiece {

    public Pawn(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("P");
    }
}
