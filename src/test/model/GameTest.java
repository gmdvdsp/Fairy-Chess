package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    String arbitraryColour = "white";
    String oppositeColour = "black";

    Square arbitraryFromSquare = new Square(0, 0);
    Square arbitraryToSquare = new Square(0, 0);

    Pawn pawn = new Pawn(arbitraryColour, false);
    Pawn otherPawn = new Pawn(oppositeColour, false);
    King king = new King(arbitraryColour);

    Game game = new Game();

    @BeforeEach
    public void runBefore() {
    }

    @Test
    public void testGame() {
        game = new Game();
    }

    @Test
    public void testGetSquareList() {
        game = new Game();
        game.getBoard();
    }

    // PAWN:
    // ========================================================
    @Test
    public void testIsLegalMovePawnEmptySpace() {
        arbitraryFromSquare.setCoordinate(0,1);
        arbitraryFromSquare.setPiece(pawn);
        arbitraryToSquare.setCoordinate(0,2);


        game.getBoard().updateBoardAt(0, 1, arbitraryFromSquare);
        game.getBoard().updateBoardAt(0, 2, arbitraryToSquare);

        assertTrue(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

    @Test
    public void testIsLegalMovePawnLegalCapture() {
        arbitraryFromSquare.setCoordinate(0,1);
        arbitraryFromSquare.setPiece(pawn);
        arbitraryToSquare.setCoordinate(1,2);
        arbitraryToSquare.setPiece(otherPawn);

        game.getBoard().updateBoardAt(0, 1, arbitraryFromSquare);
        game.getBoard().updateBoardAt(1, 2, arbitraryToSquare);

        assertTrue(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

    @Test
    public void testIsLegalMovePawnIllegalCaptureOwnPiece() {
        arbitraryFromSquare.setCoordinate(0,1);
        arbitraryFromSquare.setPiece(pawn);
        arbitraryToSquare.setCoordinate(1,2);
        arbitraryToSquare.setPiece(pawn);

        game.getBoard().updateBoardAt(0, 1, arbitraryFromSquare);
        game.getBoard().updateBoardAt(1, 2, arbitraryToSquare);

        assertFalse(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

    /*
    @Test
    public void testIsLegalMovePawnOffBoard() {
        arbitraryFromSquare.setY(7);
        arbitraryFromSquare.setPiece(pawn);
        arbitraryToSquare.setY(8);

        game.getBoard().overrideSquareAtCoordinate(0, 7, arbitraryFromSquare);
        game.getBoard().overrideSquareAtCoordinate(0, 8, arbitraryToSquare);

        assertFalse(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }
    */

    @Test
    public void testIsLegalMovePawnFirstMoveTwoMove() {
        arbitraryFromSquare.setY(1);
        arbitraryFromSquare.setPiece(pawn);
        pawn.setHasMoved(false);
        arbitraryToSquare.setY(3);

        game.getBoard().updateBoardAt(0, 1, arbitraryFromSquare);
        game.getBoard().updateBoardAt(0, 3, arbitraryToSquare);

        assertTrue(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

    @Test
    public void testIsLegalMovePawnNotFirstMoveTwoMove() {
        arbitraryFromSquare.setCoordinate(0,2);
        arbitraryFromSquare.setPiece(pawn);
        arbitraryToSquare.setCoordinate(0,4);

        game.getBoard().updateBoardAt(0, 2, arbitraryFromSquare);
        game.getBoard().updateBoardAt(0, 4, arbitraryToSquare);

        assertFalse(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

    @Test
    public void testIsLegalMovePawnMoveIntoAlliedPiece() {
        arbitraryFromSquare.setY(1);
        arbitraryFromSquare.setPiece(pawn);
        pawn.setHasMoved(false);
        arbitraryToSquare.setY(3);

        game.getBoard().updateBoardAt(0, 1, arbitraryFromSquare);
        game.getBoard().updateBoardAt(0, 3, arbitraryToSquare);

        assertTrue(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

    // KING:
    // ========================================================
    @Test
    public void testIsLegalKingMove() {
        arbitraryFromSquare.setCoordinate(5,5);
        arbitraryFromSquare.setPiece(king);
        arbitraryToSquare.setCoordinate(0,4);

        game.getBoard().updateBoardAt(0, 2, arbitraryFromSquare);
        game.getBoard().updateBoardAt(0, 4, arbitraryToSquare);

        assertFalse(game.isLegalMove(arbitraryFromSquare, arbitraryToSquare));
    }

}

