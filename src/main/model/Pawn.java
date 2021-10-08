package model;

import static java.lang.Math.abs;

public class Pawn extends BasePiece {

    private boolean hasMoved;

    public Pawn(int x, int y, String colour, Board board) {
        super(x, y, colour, board);
        hasMoved = false;
    }

    // METHODS:
    // A square denotes a single (x,y).

    // EFFECTS: Returns true if a pawn that has not moved would move to the square that is exactly two squares above
    // where the move to the square directly below that is also legal, OR if a pawn can legally capture that square,
    // OR if that square is exactly one square above the pawn and nonempty.
    @Override
    public boolean isLegalMove(int x, int y) {
        //for readability
        boolean isOneSquareAbove = (findDistanceToX(x) == 0 && findDistanceToY(y) == 1);
        boolean isTwoSquaresAbove = (findDistanceToX(x) == 0 && findDistanceToY(y) == 2);

        if (!hasMoved && board.isEmpty(x, y) && board.isEmpty(x, y - 1) && isTwoSquaresAbove) {
            return true;
        } else if (isLegalCapture(x, y)) {
            return true;
        } else if (isOneSquareAbove && board.isEmpty(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: Returns true if a pawn is one square diagonally adjacent downwards from some nonempty square AND that
    //          square is occupied by a piece of an opposite colour. Returns false in all other cases.
    @Override
    public boolean isLegalCapture(int x, int y) {
        // for readability
        boolean squareNotEmpty = !board.isEmpty(x, y);
        boolean colourIsOpposite = board.getColourOfPieceAt(x, y) != colour;
        boolean correctXDistance = (abs(findDistanceToX(x)) == 1);;
        boolean correctYDistance = findDistanceToY(y) == 1;

        return (squareNotEmpty && colourIsOpposite && correctXDistance && correctYDistance);
    }

    // GETTERS:

    public boolean getHasMoved() {
        return hasMoved;
    }

    // SETTER:

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
