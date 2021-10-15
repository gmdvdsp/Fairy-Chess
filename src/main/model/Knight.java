package model;

// Represents a knight that has some colour.
public class Knight extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a knight with a colour.
    public Knight(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("N");
    }
}
