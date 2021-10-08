package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;

import static model.Board.HEIGHT;
import static model.Board.WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    Board board;
    Pawn pawn;

    @BeforeEach
    public void runBefore() {
        board = new Board();
    }

    // Check adding a piece to a board (should make pawn a Piece instead).
    @Test
    public void testAddToBoard() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pawn = new Pawn(x, y, "white", board);

                board.addToBoard(pawn);
                assertEquals(board.getPieceAtCoordinate(x, y), pawn);
            }
        }
    }

    @Test
    public void testRemoveFromBoard() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pawn = new Pawn(x, y, "white", board);

                board.addToBoard(pawn);
            }
        }

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pawn = new Pawn(x, y, "white", board);

                board.removeFromBoard(pawn);
                assertTrue(board.isEmpty(x, y));
            }
        }
    }
}
