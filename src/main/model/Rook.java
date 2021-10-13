package model;

public class Rook extends BasePiece {
    public Rook(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("R");
    }
}
