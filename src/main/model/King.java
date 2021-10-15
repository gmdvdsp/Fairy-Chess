package model;

// Represents a king that has some colour.
public class King extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a king with a colour.
    public King(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("K");
    }
}
