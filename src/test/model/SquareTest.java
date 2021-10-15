package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {
    Square square = new Square(0, 0, null);
    Square square2 = new Square(0, 0, null);;

    @BeforeEach
    public void runBefore() {
    }

    // Tests:
    // ==================================================

    @Test
    public void testPrintSquare() {
        square.setPiece(new Pawn("white"));
        assertTrue(square.printSquare().equals("[ wP ]"));
    }

    @Test
    public void testPrintSquareEmpty() {
        assertTrue(square.printSquare().equals("[    ]"));
    }

    @Test
    public void testIsOnSameRankTrue() {
        square = new Square(4, 0, null);
        square2 = new Square(5, 0, null);
        assertTrue(square.isOnSameRank(square2));
    }

    @Test
    public void testIsOnSameRankFalse() {
        square = new Square(4, 4, null);
        square2 = new Square(5, 5, null);
        assertFalse(square.isOnSameRank(square2));
    }

    @Test
    public void testIsOnSameFileTrue() {
        square = new Square(4, 4, null);
        square2 = new Square(4, 5, null);
        assertFalse(square.isOnSameRank(square2));
    }

    @Test
    public void testIsOnSameFileFalse() {
        square = new Square(4, 4, null);
        square2 = new Square(0, 4, null);
        assertTrue(square.isOnSameRank(square2));
    }

    @Test
    public void testIsOnSameDiagonalTrue() {
        square = new Square(4, 4, null);
        square2 = new Square(6, 6, null);
        assertTrue(square.isOnSameDiagonal(square2));
    }

    @Test
    public void testIsOnSameDiagonalFalse() {
        square = new Square(4, 4, null);
        square2 = new Square(2, 3, null);
        assertFalse(square.isOnSameRank(square2));
    }

    @Test
    public void testGetDistanceBetweenNegativeDistance() {
        square = new Square(7, 7, null);
        square2 = new Square(4, 3, null);
        Square distanceSquare = square.getDistanceBetween(square2);
        assertEquals(distanceSquare.getX(), -3);
        assertEquals(distanceSquare.getY(), -4);
    }

    @Test
    public void testGetDistanceBetweenPositiveDistance() {
        square = new Square(4, 3, null);
        square2 = new Square(7, 7, null);
        Square distanceSquare = square.getDistanceBetween(square2);
        assertEquals(distanceSquare.getX(), 3);
        assertEquals(distanceSquare.getY(), 4);
    }

    @Test
    public void testGetIsEmptyTrue() {
        assertTrue(square.getIsEmpty());
    }

    @Test
    public void testGetIsEmptyFalse() {
        BasePiece pawn = new Pawn("white");
        square.setPiece(pawn);
        assertFalse(square.getIsEmpty());
    }

    @Test
    public void testGetX() {
        assertEquals(square.getX(), 0);
    }

    @Test
    public void testGetY() {
        assertEquals(square.getY(), 0);
    }

    @Test
    public void testGetPieceOnSquare() {
        BasePiece pawn = new Pawn("white");
        square.setPiece(pawn);
        assertEquals(square.getPieceOnSquare(), pawn);
    }

    @Test
    public void testGetPieceOnSquareNull() {
        assertEquals(square.getPieceOnSquare(), null);
    }

    @Test
    public void testSetX() {
        for (int x = 0; x < 9; x++) {
            square.setX(x);
            assertEquals(square.getX(), x);
        }
    }

    @Test
    public void testSetY() {
        for (int y = 0; y < 7; y++) {
            square.setY(y);
            assertEquals(square.getY(), y);
        }
    }
}
