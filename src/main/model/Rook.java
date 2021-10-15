package model;

// Represents a rook that has some colour.
public class Rook extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a new rook with a colour.
    public Rook(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("R");
    }
}
