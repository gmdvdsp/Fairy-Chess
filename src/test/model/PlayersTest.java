package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersTest {
    Players players = new Players();

    @BeforeEach
    public void runBefore() {
    }

    @Test
    public void testPlayers() {
        Players testPlayers = new Players();
        assertEquals(testPlayers.game, testPlayers.getGame());
    }

    @Test
    public void testProposedMoveTrue() {
        Square proposedFrom = new Square(0, 1, new Pawn("white"));
        Square proposedTo = new Square(0, 3, null);
        players.getGame().getBoard().defaultBoard();
        assertTrue(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseFromOffBoard() {
        Square proposedFrom = new Square(0, -7, new Pawn("white"));
        Square proposedTo = new Square(1, 1, null);
        players.getGame().getBoard().defaultBoard();
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseToOffBoard() {
        Square proposedFrom = new Square(0, 1, new Pawn("white"));
        Square proposedTo = new Square(-9, -9, null);
        players.getGame().getBoard().defaultBoard();
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseFromAndToOffBoard() {
        Square proposedFrom = new Square(-9, -9, new Pawn("white"));
        Square proposedTo = new Square(-9, -9, null);
        players.getGame().getBoard().defaultBoard();
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseFromOffNotControlledSquare() {
        players.getGame().getBoard().defaultBoard();
        Square proposedFrom = players.getGame().getBoard().getSquareAt(0, 6);
        Square proposedTo = players.getGame().getBoard().getSquareAt(0, 4);
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseFromOffOffBoardAndNotControlled() {
        Square proposedFrom = new Square(-7, -7, null);
        Square proposedTo = new Square(5, 5, null);
        players.getGame().getBoard().defaultBoard();
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testMakeMove() {
        Pawn pawn = new Pawn("black");
        Square proposedFrom = new Square(0, 1, pawn);
        Square proposedTo = new Square(0, 3, null);
        players.getGame().getBoard().defaultBoard();
        players.makeMove(proposedFrom, proposedTo);
        assertTrue(players.getGame().getBoard().getSquareAt(0, 1).getIsEmpty());
        assertEquals(players.getGame().getBoard().getSquareAt(0, 3).getPieceOnSquare(), pawn);
    }

}
