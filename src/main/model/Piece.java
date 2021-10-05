package model;

public interface Piece {
    Plane move(int coordinate);

    boolean checkValidMove(int coordinate);

    Plane capture();

    boolean isSelected();

    String getPieceColour();
}
