package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board = new Board(9, 7);
    Square square;

    @BeforeEach
    public void runBefore() {
    }

    @Test
    public void testBoard() {
        Board testBoard = new Board(9, 7);
        for (int y = 0; y <= testBoard.getYmax(); y++) {
            for (int x = 0; x <= testBoard.getXmax(); x++) {
                square = new Square(x, y, null);
                assertEquals(square.getPosX(), board.getSquareAt(x, y).getPosX());
                assertEquals(square.getPosY(), board.getSquareAt(x, y).getPosY());
                assertEquals(square.getPieceOnSquare(), board.getSquareAt(x, y).getPieceOnSquare());
            }
        }
    }

    @Test
    public void testPrintBoardDefault() {
        board.defaultBoard();
        String testBoard = board.printBoard();
        assertTrue(testBoard.equals("[ bD ][ bR ][ bN ][ bB ][ bK ][ bQ ][ bB ][ bN ][ bR ][ bI ]\n" +
                                    "[ bP ][ bP ][ bP ][ bP ][ bP ][ bP ][ bP ][ bP ][ bP ][ bP ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[ wP ][ wP ][ wP ][ wP ][ wP ][ wP ][ wP ][ wP ][ wP ][ wP ]\n" +
                                    "[ wD ][ wR ][ wN ][ wB ][ wK ][ wQ ][ wB ][ wN ][ wR ][ wI ]\n"));
    }

    @Test
    public void testPrintBoardEmpty() {
        String testBoard = board.printBoard();
        assertTrue(testBoard.equals("[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n" +
                                    "[    ][    ][    ][    ][    ][    ][    ][    ][    ][    ]\n"));
    }

    @Test
    public void isSquareOnBoardTrue() {
        square = new Square(board.getXmax(), board.getYmax(), null);
    }

    @Test
    public void isSquareOnBoardBothCoordinatesFalse() {
        square = new Square(-1, board.getYmax() + 1, null);
        assertFalse(board.isSquareOnBoard(square));
    }

    @Test
    public void isSquareOnBoardXCoordinateTooSmall() {
        square = new Square(-1, board.getYmax(), null);
        assertFalse(board.isSquareOnBoard(square));
    }

    @Test
    public void isSquareOnBoardXCoordinateTooBig() {
        square = new Square(board.getXmax() + 1, board.getYmax(), null);
        assertFalse(board.isSquareOnBoard(square));
    }

    @Test
    public void isSquareOnBoardYCoordinateTooSmall() {
        square = new Square(board.getXmax(), -1, null);
        assertFalse(board.isSquareOnBoard(square));
    }

    @Test
    public void isSquareOnBoardYCoordinateTooBig() {
        square = new Square(board.getXmax(), board.getYmax() + 1, null);
        assertFalse(board.isSquareOnBoard(square));
    }

    @Test
    public void testIsCardinalDirectionEmptyVerticalTrue() {
        board.defaultBoard();
        assertTrue(board.isCardinalDirectionEmpty(board.getSquareAt(0, 2), board.getSquareAt(0, 5)));
    }

    @Test
    public void testIsCardinalDirectionEmptyVerticalFalse() {
        board.defaultBoard();
        assertFalse(board.isCardinalDirectionEmpty(board.getSquareAt(0, 0), board.getSquareAt(0, 7)));
    }

    @Test
    public void testIsCardinalDirectionEmptyHorizontalTrue() {
        board.defaultBoard();
        assertTrue(board.isCardinalDirectionEmpty(board.getSquareAt(0, 3), board.getSquareAt(8, 3)));
    }

    @Test
    public void testIsCardinalDirectionEmptyHorizontalFalse() {
        board.defaultBoard();
        assertFalse(board.isCardinalDirectionEmpty(board.getSquareAt(0, 0), board.getSquareAt(8, 0)));
    }

    @Test
    public void testIsDiagonalDirectionEmptyUpperRightLowerLeftTrue() {
        board.defaultBoard();
        assertTrue(board.isDiagonalDirectionEmpty(board.getSquareAt(3, 3), board.getSquareAt(5, 5)));
    }

    @Test
    public void testIsDiagonalDirectionEmptyUpperRightLowerLeftFalse() {
        board.defaultBoard();
        assertFalse(board.isDiagonalDirectionEmpty(board.getSquareAt(8, 7), board.getSquareAt(1, 0)));
    }

    @Test
    public void testIsDiagonalDirectionEmptyUpperLeftLowerRightTrue() {
        board.defaultBoard();
        assertTrue(board.isDiagonalDirectionEmpty(board.getSquareAt(1, 5), board.getSquareAt(3, 3)));
    }

    @Test
    public void testIsDiagonalDirectionEmptyUpperLeftLowerRightFalse() {
        board.defaultBoard();
        assertFalse(board.isDiagonalDirectionEmpty(board.getSquareAt(0, 7), board.getSquareAt(7, 0)));
    }

    @Test
    public void testDefaultBoard() {
        board = new Board(9, 7);
        board.defaultBoard();
    }

    @Test
    public void testGetSquareAt() {
        square = board.getSquareAt(board.getXmax(), board.getYmax());
        assertEquals(square.getPosX(), board.getXmax());
        assertEquals(square.getPosY(), board.getYmax());
        assertEquals(square.getPieceOnSquare(), null);
    }

    @Test
    public void testReplaceSingleSquareEmptyBottomRow() {
        board.defaultBoard();
        for (int x = 0; x <= board.getXmax(); x++) {
            square = new Square(x, 0, null);
            board.replaceSquare(square);
            assertEquals(board.getSquareAt(x, 0), square);
        }
    }

    @Test
    public void testReplaceSingleSquarePiecesHalfRow() {
        BasePiece piece = new Pawn("white");
        board.defaultBoard();
        for (int x = 0; x <= board.getXmax(); x++) {
            square = new Square(x, board.getYmax() / 2, piece);
            board.replaceSquare(square);
            assertEquals(board.getSquareAt(x, board.getYmax() / 2), square);
        }
    }

    @Test
    public void testGetSquareList() {
        for (int y = 0; y <= board.getYmax(); y++) {
            for (int x = 0; x <= board.getXmax(); x++) {
                square = new Square(x, y, null);
                assertEquals(square.getPosX(), board.getSquareAt(x, y).getPosX());
                assertEquals(square.getPosY(), board.getSquareAt(x, y).getPosY());
                assertEquals(square.getPieceOnSquare(), board.getSquareAt(x, y).getPieceOnSquare());
            }
        }
    }

    @Test
    public void testGetXMax() {
        assertEquals(board.getXmax(), 9);
    }

    @Test
    public void testGetYMax() {
        assertEquals(board.getYmax(), 7);
    }
}
