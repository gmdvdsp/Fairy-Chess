package model;

public class Pawn extends BasePiece {

    private boolean hasMoved;

    public Pawn(String colour, boolean hasMoved) {
        super(colour);
        hasMoved = false;
    }

    // METHODS:

    // GETTERS:

    public boolean getHasMoved() {
        return hasMoved;
    }

    // SETTER:

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
