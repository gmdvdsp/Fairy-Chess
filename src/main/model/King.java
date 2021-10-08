package model;

public class King extends BasePiece {

    public King(int x, int y, String colour, Board board) {
        super(x, y, colour, board);
    }

    @Override
    protected boolean isLegalMove(int x, int y) {
        return false;
    }

    @Override
    protected boolean isLegalCapture(int x, int y) {
        return false;
    }

    // METHODS:

}
