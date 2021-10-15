package model;

// Represents a pawn that has some colour.
public class Pawn extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a pawn with a colour.
    public Pawn(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("P");
    }
}
