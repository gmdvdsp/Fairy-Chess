package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {
    Plane emptyBot = new Plane();
    Plane emptyMid = new Plane();
    Plane emptyTop = new Plane();
    //Plane stuff = something();
    Board testBoard;

    @BeforeEach
    public void runBefore() {
        Board testBoard = new Board(emptyBot, emptyMid, emptyTop);
    }

    @Test
    public void testCreateNewGameBoard() {
        testBoard.createNewGameBoard();
        //assertEquals()
    }

    @Test
    public void testUpdateBoardEmptyEmpty() {
        testBoard.updateBoard(emptyBot, emptyMid, emptyTop);
        //assertEquals()
    }

    @Test
    public void testUpdateBoardMoveFromBotToTop() {
    }
}