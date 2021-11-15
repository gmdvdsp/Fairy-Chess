package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {
    Square square = new Square(0, 0, null);
    Square square2 = new Square(0, 0, null);

    @BeforeEach
    public void runBefore() {
    }

    // Tests:
    // ==================================================

    @Test
    public void testPrintSquare() {
        square.setPiece(new Pawn("white"));
        assertEquals("[ wP ]", square.printSquare());
    }

    @Test
    public void testPrintSquareEmpty() {
        assertEquals("[    ]", square.printSquare());
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
        assertEquals(distanceSquare.getPosX(), -3);
        assertEquals(distanceSquare.getPosY(), -4);
    }

    @Test
    public void testGetDistanceBetweenPositiveDistance() {
        square = new Square(4, 3, null);
        square2 = new Square(7, 7, null);
        Square distanceSquare = square.getDistanceBetween(square2);
        assertEquals(distanceSquare.getPosX(), 3);
        assertEquals(distanceSquare.getPosY(), 4);
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
        assertEquals(square.getPosX(), 0);
    }

    @Test
    public void testGetY() {
        assertEquals(square.getPosY(), 0);
    }

    @Test
    public void testGetPieceOnSquare() {
        BasePiece pawn = new Pawn("white");
        square.setPiece(pawn);
        assertEquals(square.getPieceOnSquare(), pawn);
    }

    @Test
    public void testGetPieceOnSquareNull() {
        assertNull(square.getPieceOnSquare());
    }

    @Test
    public void testGetOriginalColor() {
        assertNull(square.getOriginalColor());
    }

    @Test
    public void testSetX() {
        for (int x = 0; x < 9; x++) {
            square.setX(x);
            assertEquals(square.getPosX(), x);
        }
    }

    @Test
    public void testSetY() {
        for (int y = 0; y < 7; y++) {
            square.setY(y);
            assertEquals(square.getPosY(), y);
        }
    }

    @Test
    public void testSetOriginalColor() {
        square.setOriginalColor(Color.BLACK);
        assertEquals(square.getOriginalColor(), Color.BLACK);
    }
}
