package model;

// Represents a princess that has some colour.
public class Princess extends BasePiece {
    // MODIFIES: this
    // EFFECTS: Makes a new princess with a colour.
    public Princess(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("I");
    }
}
