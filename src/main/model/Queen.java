package model;

// Represents a queen that has some colour.
public class Queen extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a new queen with a colour.
    public Queen(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("Q");
    }
}
