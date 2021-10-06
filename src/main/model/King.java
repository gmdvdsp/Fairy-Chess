package model;

public class King implements Piece {

    public King() {
    }

    // METHODS:

    public void move(Plane newPlane, int coordinate) {
    }

    public boolean checkValidMove(Plane newPlane, int coordinate) {
        return false;
    }

    @Override
    public Integer getDistanceInX(int x) {
        return 0;
    }

    @Override
    public Integer getDistanceInY(int y) {
        return 0;
    }

    public Plane capture() {
        return null;
    }

    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isOnPlane(Plane p) {
        return false;
    }

    public String getPieceColour() {
        return "";
    }

    public boolean wasCaptured() {
        return false;
    }

    @Override
    public Integer getCurrentLevelOfPlane() {
        return 0;
    }

    public Integer getX() {
        return 0;
    }

    public Integer getY() {
        return 0;
    }

    @Override
    public String getColumn() {
        return null;
    }

    public void setPieceLevel(int level) {
    }

    public void setCoordinate(int coordinate) {
    }

    @Override
    public void setMaxMovement(int maxMovement) {

    }

    public void setColour(String colour) {
    }
}
