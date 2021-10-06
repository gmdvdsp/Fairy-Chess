package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {

    // Arbitrary point.
    int arbitraryX = 3;
    int arbitraryY = 3;

    // Arbitrary points corresponding to the diagonals surrounding (arbitraryX, arbitraryY). These can go off the legal
    // coordinates, but things will behave properly anyway, so this shouldn't be a problem.
    int topRightX = arbitraryX + 1;
    int topRightY = arbitraryY + 1;

    int topLeftX = arbitraryX - 1;
    int topLeftY = arbitraryY + 1;

    int botRightX = arbitraryX + 1;
    int botRightY = arbitraryY - 1;

    int botLeftX = arbitraryX - 1;
    int botLeftY = arbitraryY - 1;

    // Arbitrary points corresponding to the cardinal squares surrounding (arbitraryX, arbitraryY).
    int topY = arbitraryY + 1;

    int leftX = arbitraryX - 1;

    int rightX = arbitraryX + 1;

    int botY = arbitraryY - 1;

    // Arbitrary colors.
    String arbitraryColour = "black";
    String oppositeColour = "white"; //Must be the opposite of arbitraryColour

    // Arbitrary piece to test captures and move legality.
    Piece arbitraryPiece = new Pawn();

    Plane originPlane;
    Plane toPlane;

    Bishop testBishop;

    @BeforeEach
    public void runBefore() {
        originPlane = new Plane();
        toPlane = new Plane();
    }

    // Test to move piece to a location on an empty plane.

    @Test
    public void testMoveToRightUp() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        // Move diagonally right up.
        assertTrue(testBishop.checkValidMove(originPlane, topRightX, topRightY));
        testBishop.move(originPlane, topRightX, topRightY);
        assertEquals(testBishop.getX(), topRightX);
        assertEquals(testBishop.getY(), topRightY);
    }

    @Test
    public void testMoveToLeftUp() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        // Move diagonally left up.
        assertTrue(testBishop.checkValidMove(originPlane, topRightX, topRightY));
        testBishop.move(originPlane, topRightX, topRightY);
        assertEquals(testBishop.getX(), topLeftX);
        assertEquals(testBishop.getY(), topLeftY);
    }

    @Test
    public void testMoveToRightDown() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        // Move diagonally left up.
        assertTrue(testBishop.checkValidMove(originPlane, topRightX, topRightY));
        testBishop.move(originPlane, botRightX, botRightY);
        assertEquals(testBishop.getX(), botRightX);
        assertEquals(testBishop.getY(), botRightY);
    }

    @Test
    public void testMoveToLeftDown() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        // Move diagonally left up.
        assertTrue(testBishop.checkValidMove(originPlane, topRightX, topRightY));
        testBishop.move(originPlane, topRightX, topRightY);
        assertEquals(testBishop.getX(), botLeftX);
        assertEquals(testBishop.getY(), botLeftY);
    }

    // Check legal moves on empty planes.

    @Test
    public void testCheckValidMoveLegalMoveRightUp() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        for (int i = 1; i < 7; i++) {
            if (testBishop.isOnPlane(testBishop.getX() + i, testBishop.getY() + i)); {
                assertTrue(testBishop.checkValidMove(originPlane, testBishop.getX() + i, testBishop.getY() + i));
            }
        }
    }

    @Test
    public void testCheckValidMoveLegalMoveLeftUp() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        for (int i = 1; i < 7; i++) {
            if (testBishop.isOnPlane(testBishop.getX() - i, testBishop.getY() + i)); {
                assertTrue(testBishop.checkValidMove(originPlane, testBishop.getX() - i, testBishop.getY() + i));
            }
        }
    }

    @Test
    public void testCheckValidMoveLegalMoveRightDown() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        for (int i = 1; i < 7; i++) {
            if (testBishop.isOnPlane(testBishop.getX() + i, testBishop.getY() - i)); {
                assertTrue(testBishop.checkValidMove(originPlane, testBishop.getX() + i, testBishop.getY() - i));
            }
        }
    }

    @Test
    public void testCheckValidMoveLegalMoveLeftDown() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        for (int i = 1; i < 7; i++) {
            if (testBishop.isOnPlane(testBishop.getX() - i, testBishop.getY() - i)); {
                assertTrue(testBishop.checkValidMove(originPlane, testBishop.getX() - i, testBishop.getY() - i));
            }
        }
    }

    // Check illegal moves from only piece restrictions on an empty plane.

    @Test
    public void testCheckValidMoveIllegalNoMove() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        assertFalse(testBishop.checkValidMove(originPlane, testBishop.getX(), testBishop.getY());
    }

    @Test
    public void testCheckValidMoveIllegal() {
        testBishop.setCoordinate(arbitraryX, arbitraryY);

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (!(abs(x - testBishop.getX()) == abs(y - testBishop.getY()))) {
                    assertFalse(testBishop.checkValidMove(originPlane, x, y));
                }
            }
        }
    }

    // Check illegal move due to landing on a square already occupied by a piece of the same colour.

    // Check illegal move due to there being a piece in the way.

    // Checks captures.

    @Test
    public void testCapture() {
        // Set starting position.
        arbitraryPiece.setColour(arbitraryColour);
        testBishop.setColour(oppositeColour);

        testBishop.setCoordinate(arbitraryX, arbitraryY);
        arbitraryPiece.setCoordinate(arbitraryX, arbitraryY);

        testBishop.capture();
        assertTrue(arbitraryPiece.wasCaptured());
        assertEquals(testBishop.getX(), arbitraryX);
        assertEquals(testBishop.getY(), arbitraryY);
    }
}
