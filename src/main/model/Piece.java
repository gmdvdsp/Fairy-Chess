package model;

public interface Piece {
    void move(Plane newPlane, Coordinate coordinate);

    boolean checkValidMove(Plane newPlane, int x, int y);

    Plane capture();

    boolean isSelected();

    boolean isOnPlane(int x, int y);

    boolean wasCaptured();

    String getPieceColour();

    Integer getCurrentLevelOfPlane();

    Integer getX();

    Integer getY();

    String getColumn();

    void setColour(String colour);

    // REQUIRES: level is one of 1, 2, 3.
    void setPieceLevel(int level);

    void setCoordinate(int x, int y);

    void setMaxMovement(int maxMovement);

    Integer getDistanceInX(int x);

    Integer getDistanceInY(int y);
}
