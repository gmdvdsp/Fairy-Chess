package model;

public class Bishop extends BasePiece {
    public Bishop(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("B");
    }
}
