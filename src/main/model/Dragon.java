package model;

// Represents a dragon that has some colour.
public class Dragon extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a dragon with a colour.
    public Dragon(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("D");
    }
}
