package model;

public class Dragon extends BasePiece {
    public Dragon(String colour) {
        super(colour);
    }

    @Override
    protected String printPiece() {
        return ("D");
    }
}
