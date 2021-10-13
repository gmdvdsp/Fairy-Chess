package model;

public class Princess extends BasePiece {
    public Princess(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("I");
    }
}
