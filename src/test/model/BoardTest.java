package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

import static model.Board.SQUARES_ON_COLUMN;
import static model.Board.SQUARES_ON_ROW;
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

    private int currentSquare = -1;
    private List<BasePiece> comparisonBoard;
    private Board board;
    private BasePiece arbitraryPiece;

    @BeforeEach
    public void runBefore() {
        arbitraryPiece = new Pawn(0, 0, "white", board);
        board = new Board();
    }

    // Check adding a piece to a board (should make pawn a Piece instead).
    @Test
    public void testAddToBoardAndGetPieceAtCoordinate() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                currentSquare += 1;
                if (currentSquare % alternatingInterval == 0) {
                    arbitraryPiece.setCoordinate(x, y);
                    board.addToBoard(arbitraryPiece);
                    assertEquals(board.getPieceAtCoordinate(x, y), arbitraryPiece);
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
                board.removeFromBoard(board.getPieceAtCoordinate(x, y));
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
}
