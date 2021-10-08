package model;

import static java.lang.Math.abs;
import static model.Board.WIDTH;
import static model.Board.HEIGHT;

public class Pawn implements Piece {

    int posX;
    int posY;
    String colour;
    boolean isSelected;
    boolean isCaptured;
    boolean hasMoved;
    Board board;

    public Pawn(int x, int y, String colour, Board board) {
        this.posX = x;
        this.posY = y;
        this.colour = colour;
        isSelected = false;
        isCaptured = false;
        hasMoved = false;
        this.board = board;
    }

    // METHODS:
    // A square denotes a single (x,y).

    // MODIFIES: this
    // EFFECTS: Moves this piece to square if the move is "valid" AND the piece is selected, and if the move was a
    //          capture, declare the piece as captured, and remove it from the board.
    public void move(int x, int y) {
        if (isValidMove(x, y) && isSelected) {
            if (isLegalCapture(x, y)) {
                board.removeFromBoard(board.getPieceAtCoordinate(x, y));
            }
            posX = x;
            posY = y;
            isSelected = false;
            if (hasMoved) {
                hasMoved = false;
            }
            board.addToBoard(this);
        }
    }

    // EFFECTS: Returns true if a move to a square is both "legal" AND on the board. Returns false in all other
    //          cases.
    public boolean isValidMove(int x, int y) {
        return (board.isMoveOnBoard(x, y) && isLegalMove(x, y));
    }

    // EFFECTS: Returns true if a pawn that has not moved would move to the square that is exactly two squares above
    // where the move to the square directly below that is also legal, OR if a pawn can legally capture that square,
    // OR if that square is exactly one square above the pawn and nonempty.
    public boolean isLegalMove(int x, int y) {
        //for readability
        boolean isOneSquareAbove = (findDistanceToX(x) == 0 && findDistanceToY(y) == 1);
        boolean isTwoSquaresAbove = (findDistanceToX(x) == 0 && findDistanceToY(y) == 2);
        boolean isOneSquareMoveLegal = isLegalMove(x, y - 1);


        if (!hasMoved && isOneSquareMoveLegal && isTwoSquaresAbove && board.isEmpty(x, y)) {
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
    public boolean isLegalCapture(int x, int y) {
        // for readability
        boolean squareNotEmpty = !board.isEmpty(x, y);
        boolean colourIsOpposite = board.getColourOfPieceAt(x, y) != colour;
        boolean correctXDistance = (abs(findDistanceToX(x)) == 1);;
        boolean correctYDistance = findDistanceToY(y) == 1;

        return (squareNotEmpty && colourIsOpposite && correctXDistance && correctYDistance);
    }

    // EFFECTS: Returns the distance in the x-direction from a point x to the pawn's current position, where if x is to
    //          the left of the pawn's current position, returns a negative number, and if right, returns a positive.
    //          Returns zero if x = pawn's current x.
    public Integer findDistanceToX(int x) {
        return (x - posX);
    }

    // EFFECTS: Returns the distance in the y-direction from a point y to the pawn's current position, where if y is
    //          above the pawn's current position, returns a positive number, and if below, returns a negative. Returns
    //          zero if y = pawn's current y.
    public Integer findDistanceToY(int y) {
        return (y - posY);
    }

    // REQUIRES: isCaptured == true
    // MODIFIES: this
    // EFFECTS: makes this object inaccessible by putting it in some coordinate off the board. It is effectively gone
    //          from the game.
    public void die() {
        if (this.isCaptured) {
            posX = WIDTH + 1;
            posY = HEIGHT + 1;
        }
    }

    // GETTERS:
    public Integer getX() {
        return posX;
    }

    public Integer getY() {
        return posY;
    }

    public String getColour() {
        return colour;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public boolean getIsCaptured() {
        return isCaptured;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public Board getBoard() {
        return board;
    }

    // SETTERS:
    public void setCoordinate(int x, int y) {
        posX = x;
        posY = y;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setIsCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
