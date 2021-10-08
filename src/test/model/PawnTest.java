package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;

import static model.Board.*;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    // Arbitrary point
    private int arbitraryX = 1;
    private int arbitraryY = 1;

    // Arbitrary status of isSelected.
    private boolean arbitraryIsSelected = true;

    // Arbitrary status of hasMoved.
    private boolean arbitraryHasMoved = false;

    // Arbitrary colors.
    private String arbitraryColour = "black";
    private String oppositeColour = "white"; //Must be the opposite of arbitraryColour.

    // Arbitrary piece to test captures and move legality.
    private Pawn arbitraryPiece;

    private Board originBoard;
    private Pawn pawn;

    @BeforeEach
    public void runBefore() {
        originBoard = new Board();
        pawn = new Pawn(arbitraryX, arbitraryY, arbitraryColour, originBoard);
        pawn.setIsSelected(true);
    }

    // Constructor test.
    public void testPawn() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                Pawn constructorPawn = new Pawn(0, 0, arbitraryColour, originBoard);
                assertEquals(constructorPawn.getX(), x);
                assertEquals(constructorPawn.getY(), y);
                assertEquals(constructorPawn.getColour(), arbitraryColour);
                assertEquals(constructorPawn.getIsSelected(), false);
                assertEquals(constructorPawn.getIsCaptured(), false);
                assertEquals(constructorPawn.getHasMoved(), false);
                assertEquals(constructorPawn.getBoard(), originBoard);
            }
        }
    }


    // Check that a pawn moves to some square if that move is valid and the piece is selected.
    @Test
    public void testMove() {
        for (int y = 0; y < MAX_Y_COORDINATE; y++) {
            for (int x = 0; x < MAX_X_COORDINATE; x++) {
                pawn.setIsSelected(true);
                pawn.setCoordinate(arbitraryX, arbitraryY);

                if (pawn.getIsSelected() && pawn.isValidMove(x, y)) {
                    pawn.move(x, y);
                    pawn.setHasMoved(true);
                    pawn.setIsSelected(false);
                    assertEquals(pawn.getX(), x);
                    assertEquals(pawn.getY(), y);
                    assertTrue(pawn.getHasMoved());
                    assertFalse(pawn.getIsSelected());
                    assertEquals(originBoard.getPieceAtCoordinate(x, y), pawn);
                } else {
                    pawn.move(x, y);
                    assertEquals(pawn.getX(), arbitraryX);
                    assertEquals(pawn.getY(), arbitraryY);
                }
            }
        }
    }

    // Check that a legal move is valid (on the board).
    // ====================================
    // Check that a move is valid, ONLY if BOTH: the move is legal, and the
    // coordinate is on the board.
    @Test
    public void testIsValidMove() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (originBoard.isMoveOnBoard(x, y) && pawn.isLegalMove(x, y)) {
                    assertTrue(pawn.isValidMove(x, y));
                } else {
                    assertFalse(pawn.isValidMove(x, y));
                }
            }
        }
    }

    // Check ways that a move can be legal (isLegalMovement is true):
    // ====================================
    // Check that it is legal for a pawn to move to a square, IF BOTH the square is empty, and the square is exactly
    // one up.
    @Test
    public void testIsLegalMove() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (originBoard.isEmpty(x, y) && (pawn.findDistanceToX(x) == 0 && pawn.findDistanceToY(y) == 1)) {
                    assertTrue(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check that it is legal for a pawn to move twice ahead, IF ALL of: that square is empty, the pawn has not yet
    // moved, and it is legal for the pawn to move once ahead.
    @Test
    public void testIsLegalMoveTwoSquaresAhead() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (!pawn.getHasMoved() && originBoard.isEmpty(x, y - 1) && originBoard.isEmpty(x, y)) {
                    assertTrue(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check that it is legal for a pawn to move to a square, IF: the pawn can legally capture
    // that square.
    @Test
    public void testIsLegalMoveCapture() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (pawn.isLegalCapture(x, y)) {
                    assertTrue(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check ways that a move can be illegal (isLegalMovement is false):
    // ====================================
    // Check that it is illegal for a pawn to move to a square that doesn't even comply with it's original movement in
    // chess, if ONE OF: the distance moved in x is no longer 0, the distance moved in y is not 1 or 2, AND: the pawn
    // cannot capture that square.
    @Test
    public void testIsLegalMoveDoesNotComplyWithMovement() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (pawn.findDistanceToX(x) != 0 || !(pawn.findDistanceToY(y) == 1 || pawn.findDistanceToY(y) == 2) && !pawn.isLegalCapture(x, y)) {
                    assertFalse(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check that it is illegal for a pawn to move to a square that is occupied by a piece if BOTH: the
    // square is not empty, and the pawn cannot legally capture to that square.
    @Test
    public void testIsLegalMoveIsOccupied() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (!originBoard.isEmpty(x, y) && !pawn.isLegalCapture(x, y)) {
                    assertFalse(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check that it is illegal for a pawn to move two ahead, IF: the pawn has already moved, and the distance between
    // the pawn's current position and the candidate move's y-value is +2.
    @Test
    public void testIsLegalMoveTwoSquaresAheadAlreadyMoved() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (pawn.getHasMoved() == true && (pawn.findDistanceToY(y) == 2)) {
                    assertFalse(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check that it is illegal for a pawn to move to a square it's already on, IF: the square's x and
    // y-values are the same as the square the pawn is already on.
    @Test
    public void testIsLegalMoveNoMove() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                if (pawn.findDistanceToX(x) == 0 && pawn.findDistanceToY(y) == 0) {
                    assertFalse(pawn.isLegalMove(x, y));
                }
            }
        }
    }

    // Check ways that a pawn can capture (legalCapture is true)
    // ====================================
    // Check that a pawn capture to a square is legal IF ALL of: the square is not empty, the piece on the square has
    // the opposite colour of the pawn, the square has distance -1 or +1 from the pawn's current x-position, and the
    // square has distance +1 from the pawn's current y-position.
    @Test
    public void testIsLegalCapture() {
        arbitraryPiece = new Pawn(arbitraryX, arbitraryY, oppositeColour, originBoard);
        // for readability
        boolean squareNotEmpty;
        boolean colourIsOpposite;
        boolean correctXDistance;
        boolean correctYDistance;

        for (int x = 0; x < MAX_X_COORDINATE; x++) {
            for (int y = 0; y < MAX_Y_COORDINATE; y++) {
                arbitraryPiece.setCoordinate(x, y);
                originBoard.addToBoard(arbitraryPiece);

                // for readability
                squareNotEmpty = !originBoard.isEmpty(x, y);
                colourIsOpposite =  originBoard.getColourOfPieceAt(x, y).equals(oppositeColour);
                correctXDistance = (abs(pawn.findDistanceToX(x)) == 1);
                correctYDistance = pawn.findDistanceToY(y) == 1;

                if (squareNotEmpty && colourIsOpposite && correctXDistance && correctYDistance) {
                    assertTrue(pawn.isLegalCapture(x, y));
                } else {
                    assertFalse(pawn.isLegalCapture(x, y));
                }
            }
        }
    }

    // Check the distance methods
    // ====================================
    @Test
    public void testFindDistanceToX() {
        for (int x = 0; x < MAX_X_COORDINATE; x++) {
                assertEquals(pawn.findDistanceToX(x), x - pawn.getX());
            }
    }

    @Test
    public void testFindDistanceToY() {
        for (int y = 0; y < MAX_Y_COORDINATE; y++) {
            assertEquals(pawn.findDistanceToY(y), y - pawn.getY());
        }
    }

    // Check die
    // ====================================
    @Test
    public void testDie() {
        pawn.die();
        assertTrue(pawn.getIsCaptured());
        assertEquals(pawn.getX(), MAX_X_COORDINATE + 1);
        assertEquals(pawn.getY(), MAX_Y_COORDINATE + 1);
    }

    // Check getters
    // ====================================
    @Test
    public void testGetX() {
        assertEquals(pawn.getX(), arbitraryX);
    }

    @Test
    public void testGetY() {
        assertEquals(pawn.getY(), arbitraryY);
    }

    @Test
    public void testGetColour() {
        assertEquals(pawn.getColour(), arbitraryColour);
    }

    @Test
    public void testGetHasMoved() {
        assertEquals(pawn.getHasMoved(), arbitraryHasMoved);
    }

    @Test
    public void testGetIsSelected() {
        if (arbitraryIsSelected == true) {
            assertTrue(pawn.getIsSelected());
        } else {
            assertFalse(pawn.getIsSelected());
        }
    }

    @Test
    public void testGetIsCaptured() {
        pawn.setIsCaptured(true);
        assertTrue(pawn.getIsSelected());
    }

    @Test
    public void testGetBoard() {
        assertEquals(pawn.getBoard(), originBoard);
    }

    // Check setters
    // ====================================
    @Test
    public void testSetCoordinate() {
        pawn.setCoordinate(arbitraryY, arbitraryX);
        assertEquals(pawn.getX(), arbitraryY);
        assertEquals(pawn.getY(), arbitraryX);
    }

    @Test
    public void testSetColour() {
        pawn.setColour(oppositeColour);
        assertEquals(pawn.getColour(), oppositeColour);
    }

    @Test
    public void testSetHasMoved() {
        pawn.setHasMoved(!arbitraryHasMoved);
        if (!arbitraryHasMoved) {
            assertTrue(pawn.getHasMoved());
        } else {
            assertFalse(pawn.getHasMoved());
        }
    }

    @Test
    public void testSetIsSelected() {
        pawn.setIsSelected(arbitraryIsSelected);
        if (arbitraryIsSelected) {
            assertTrue(pawn.getIsSelected());
        } else {
            assertFalse(pawn.getIsSelected());
        }
    }

    @Test
    public void testSetIsCaptured() {
        pawn.setIsCaptured(false);
        assertFalse(pawn.getIsCaptured());
        pawn.setIsCaptured(true);
        assertTrue(pawn.getIsCaptured());
    }

    @Test
    public void testSetBoard() {
        Board toBoard = new Board();
        pawn.setBoard(toBoard);
        assertEquals(pawn.getBoard(), toBoard);
    }
}