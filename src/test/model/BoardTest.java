package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static model.Board.MAX_X_COORDINATE;
import static model.Board.MAX_Y_COORDINATE;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // Arbitrary interval to add pieces.
    private int alternatingInterval = 2;

    // Arbitrary threshold for testing board limits.
    private int arbitraryThreshold = 20;

    // Arbitrary colors.
    private String arbitraryColour = "black";
    private String oppositeColour = "white"; //Must be the opposite of arbitraryColour.

    private Board board = new Board();

    @BeforeEach
    public void runBefore() {
    }

    @Test
    public void testDefaultBoard() {
        board = new Board();
        board.defaultBoard();
    }

    @Test
    public void testPrintBoard() {
        board.defaultBoard();
        String testBoard = board.printBoard();
        assertTrue(testBoard.equals(""));
    }
}
