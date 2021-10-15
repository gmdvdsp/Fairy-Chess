package model;

// Represents a bishop that has some colour.
public class Bishop extends BasePiece {
    public Bishop(String colour) {
        super(colour);
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("B");
    }
}
