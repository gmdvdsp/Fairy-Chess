package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.abs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    // Arbitrary interval to add pieces.
    private int alternatingInterval = 2;

    // Arbitrary threshold for testing board limits.
    private int arbitraryThreshold = 20;

    // Arbitrary colors.
    private String arbitraryColour = "black";
    private String oppositeColour = "white"; //Must be the opposite of arbitraryColour.

    private Game game;
    private Board board;
    private Square fromSquare;
    private Square toSquare;

    private Pawn pawn;
    private Pawn otherPawn;

    @BeforeEach
    public void runBefore() {
        board = new Board();
        game = new Game();
        pawn = new Pawn(arbitraryColour, false);
        pawn = new Pawn(oppositeColour, false);
        fromSquare.setPiece(pawn);
        toSquare.setPiece(otherPawn);
    }

    /*
    // Check adding a piece to a board (should make pawn a Piece instead).
    @Test
    public void testAddToBoardAndGetPieceAtCoordinate() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    arbitraryPiece.setCoordinate(x, y);
                    board.addToBoard(arbitraryPiece);
                    assertEquals(board.getPieceOnSquare(x, y), arbitraryPiece);
                }
            }
        }
    }

    // Check removing a piece from a board.
    @Test
    public void testRemoveFromBoard() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                    arbitraryPiece.setCoordinate(x, y);
                    board.addToBoard(arbitraryPiece);
            }
        }

        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                board.removeFromBoard(board.getPieceOnSquare(x, y));
                assertTrue(board.isEmpty(x, y));
            }
        }
    }

    @Test
    public void testIsEmpty() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    arbitraryPiece = new Pawn(x, y, "white", board);
                    board.addToBoard(arbitraryPiece);
                }
            }
        }
        currentSquare = -1;
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    assertFalse(board.isEmpty(x, y));
                } else {
                    assertTrue(board.isEmpty(x, y));
                }
            }
        }
    }

    @Test
    public void testCreateEmptyBoard() {

    }

    @Test
    public void testGetBoardEmpty() {
        assertEquals(board.getBoard(), Collections.emptyList());
    }

    @Test
    public void testGetBoard() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    arbitraryPiece.setCoordinate(x, y);
                    board.addToBoard(arbitraryPiece);
                    comparisonBoard.add(arbitraryPiece);
                }
                assertEquals(board.getBoard(), comparisonBoard);
            }
        }
    }

    @Test
    public void testCountBoardEmpty() {
        assertEquals(board.countBoard(), 0);
    }

    @Test
    public void testCountBoard() {
        int piecesAdded = 0;
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                board.addToBoard(arbitraryPiece);
                piecesAdded++;
                currentSquare += 1;
                assertEquals(board.countBoard(), piecesAdded);
            }
        }
    }

    @Test
    public void testIsMoveOnBoard() {
        for (int y = -arbitraryThreshold; y < SQUARES_ON_COLUMN + arbitraryThreshold; y++) {
            for (int x = -arbitraryThreshold; x < SQUARES_ON_ROW + arbitraryThreshold; x++) {
                if (board.isMoveOnBoard(x, y)) {
                    assertTrue(board.isMoveOnBoard(x, y));
                } else {
                    assertFalse(board.isMoveOnBoard(x, y));
                }
            }
        }
    }

    @Test
    public void testGetColourOfPieceAt() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    arbitraryPiece = new Pawn(x, y, arbitraryColour, board);
                    board.addToBoard(arbitraryPiece);
                } else {
                    arbitraryPiece = new Pawn(x, y, oppositeColour, board);
                    board.addToBoard(arbitraryPiece);
                }
            }
        }
        currentSquare = -1;
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    assertEquals(board.getColourOfPieceAt(x, y), arbitraryColour);
                } else {
                    assertEquals(board.getColourOfPieceAt(x, y), oppositeColour);
                }
            }
        }
    }
    */
}
