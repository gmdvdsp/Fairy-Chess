package model;

public class King extends BasePiece {
    public King(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("K");
    }
}
