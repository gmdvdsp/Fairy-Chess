package model;

public class Knight extends BasePiece {
    public Knight(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("N");
    }
}
