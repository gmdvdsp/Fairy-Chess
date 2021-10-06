package model;

// Represents a bishop piece of either colour.
public class Bishop implements Piece {

    public Bishop() {
    }

    // METHODS:

    public void move(Plane newPlane, int x, int y) {
    }

    @Override
    public Integer getDistanceInX(int x) {
        return 0;
    }

    @Override
    public Integer getDistanceInY(int y) {
        return 0;
    }

    public boolean checkValidMove(Plane newPlane, int x, int y) {
        return false;
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

    public void setCoordinate(int x, int y) {
    }

    @Override
    public void setMaxMovement(int maxMovement) {

    }

    public void setColour(String colour) {
    }
}
