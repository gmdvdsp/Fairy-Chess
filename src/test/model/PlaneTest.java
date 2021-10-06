package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaneTest {
    private final static int MAX_BOARD_SIZE = 24;

    Plane emptyBot = new Plane();
    Plane emptyMid = new Plane();
    Plane emptyTop = new Plane();

    Pawn pawn = new Pawn();
    Knight knight = new Knight();

    List<Piece> planeList;

    @BeforeEach
    public void runBefore() {

    }

    @Test
    public void testUpdatePlane() {
    }

    @Test
    public void testUpdatePieceToPlaneFromSamePlane() {
    }

    @Test
    public void testUpdatePlanePieceToPlaneFromDifferentPlane() {
    }

    @Test
    public void getPieceAtCoordinateNoPieceExists() {
    }

    @Test
    public void getPieceAtCoordinateOnlyOneOnPieceOnPlane() {
    }

    @Test
    public void getPieceAtCoordinatePieceEveryPieceOnPlane() {
    }

    @Test
    public void testGetPlane() {
    }

}
