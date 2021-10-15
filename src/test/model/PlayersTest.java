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
    public void testProposedMoveFalseOffBoard() {
        Square proposedFrom = new Square(0, -7, new Pawn("white"));
        Square proposedTo = new Square(-34, 1, null);
        players.getGame().getBoard().defaultBoard();
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseOffNotControlledSquare() {
        players.getGame().getBoard().defaultBoard();
        Square proposedFrom = players.getGame().getBoard().getSquareAt(0, 6);
        Square proposedTo = players.getGame().getBoard().getSquareAt(0, 4);
        assertFalse(players.proposeMove(proposedFrom, proposedTo));
    }

    @Test
    public void testProposedMoveFalseOffOffBoardAndNotControlled() {
        Square proposedFrom = new Square(-7, -7, null);
        Square proposedTo = new Square(-34, -19, null);
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
