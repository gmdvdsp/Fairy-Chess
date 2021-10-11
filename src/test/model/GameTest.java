package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    Square to = new Square(0, 0, null);
    Square from = new Square(0, 0, null);

    Pawn whitePawn = new Pawn("white", false);
    Pawn blackPawn = new Pawn("black", false);
    King whiteKing = new King("white");
    King blackKing = new King("white");

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

    // PAWN (white):
    // ========================================================
    @Test
    public void testIsLegalMoveWhitePawnOneMove() {
        from.setCoordinate(0,1);
        from.setPiece(whitePawn);
        to.setCoordinate(0,2);


        game.getBoard().updateBoardAt(0, 1, from);
        game.getBoard().updateBoardAt(0, 2, to);

        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnWrongWay() {
        from.setCoordinate(0,2);
        from.setPiece(whitePawn);
        to.setCoordinate(0,1);


        game.getBoard().updateBoardAt(0, 2, from);
        game.getBoard().updateBoardAt(0, 1, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnLegalCapture() {
        from.setCoordinate(0,1);
        from.setPiece(whitePawn);
        to.setCoordinate(1,2);
        to.setPiece(blackPawn);

        game.getBoard().updateBoardAt(0, 1, from);
        game.getBoard().updateBoardAt(1, 2, to);

        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnIllegalCaptureOwnPiece() {
        from.setCoordinate(0,1);
        from.setPiece(whitePawn);
        to.setCoordinate(1,2);
        to.setPiece(whitePawn);

        game.getBoard().updateBoardAt(0, 1, from);
        game.getBoard().updateBoardAt(1, 2, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnFirstMoveTwoMove() {
        from.setCoordinate(0,1);
        from.setPiece(whitePawn);
        to.setCoordinate(0,3);

        game.getBoard().updateBoardAt(0, 1, from);
        game.getBoard().updateBoardAt(0, 3, to);

        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnFirstMoveTwoMoveBlocked() {
        Square block = new Square(8, 5, whitePawn);
        from.setCoordinate(8,6);
        from.setPiece(whitePawn);
        to.setCoordinate(8,4);

        game.getBoard().updateBoardAt(8, 6, from);
        game.getBoard().updateBoardAt(8,5, block);
        game.getBoard().updateBoardAt(8, 4, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnNotFirstMoveTwoMove() {
        to.setCoordinate(0,2);
        to.setPiece(whitePawn);
        from.setCoordinate(0,4);

        game.getBoard().updateBoardAt(0, 2, to);
        game.getBoard().updateBoardAt(0, 4, from);

        assertFalse(game.isLegalMove(to, from));
    }

    @Test
    public void testIsLegalMoveWhitePawnMoveIntoAlliedPiece() {
        from.setCoordinate(0,1);
        from.setPiece(whitePawn);
        to.setCoordinate(0,2);
        to.setPiece(whitePawn);

        game.getBoard().updateBoardAt(0, 1, from);
        game.getBoard().updateBoardAt(0, 2, to);

        assertFalse(game.isLegalMove(from, to));
    }

    // Pawn (Black):
    // ========================================================
    @Test
    public void testIsLegalMoveBlackPawnOneMove() {
        from.setCoordinate(8,7);
        from.setPiece(blackPawn);
        to.setCoordinate(8,6);


        game.getBoard().updateBoardAt(8, 7, from);
        game.getBoard().updateBoardAt(8, 6, to);

        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnWrongWay() {
        from.setCoordinate(8,6);
        from.setPiece(blackPawn);
        to.setCoordinate(8,7);


        game.getBoard().updateBoardAt(8, 6, from);
        game.getBoard().updateBoardAt(8, 7, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnLegalCapture() {
        from.setCoordinate(8,7);
        from.setPiece(blackPawn);
        to.setCoordinate(7,6);
        to.setPiece(whitePawn);

        game.getBoard().updateBoardAt(8, 7, from);
        game.getBoard().updateBoardAt(7, 6, to);

        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBackBlackPawnIllegalCaptureOwnPiece() {
        from.setCoordinate(8,7);
        from.setPiece(blackPawn);
        to.setCoordinate(7,6);
        to.setPiece(blackPawn);

        game.getBoard().updateBoardAt(8, 7, from);
        game.getBoard().updateBoardAt(7, 6, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnFirstMoveTwoMove() {
        from.setCoordinate(8,6);
        from.setPiece(blackPawn);
        to.setCoordinate(8,4);

        game.getBoard().updateBoardAt(8, 6, from);
        game.getBoard().updateBoardAt(8, 4, to);

        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnFirstMoveTwoMoveBlocked() {
        Square block = new Square(8, 5, blackPawn);
        from.setCoordinate(8,6);
        from.setPiece(blackPawn);
        to.setCoordinate(8,4);

        game.getBoard().updateBoardAt(8, 6, from);
        game.getBoard().updateBoardAt(8,5, block);
        game.getBoard().updateBoardAt(8, 4, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnNotFirstMoveTwoMove() {
        from.setCoordinate(8,5);
        from.setPiece(blackPawn);
        to.setCoordinate(8,3);

        game.getBoard().updateBoardAt(8, 5, from);
        game.getBoard().updateBoardAt(8, 3, to);

        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnMoveIntoAlliedPiece() {
        from.setCoordinate(8,7);
        from.setPiece(whitePawn);
        to.setCoordinate(8,6);
        to.setPiece(whitePawn);

        game.getBoard().updateBoardAt(8, 7, from);
        game.getBoard().updateBoardAt(8, 6, to);

        assertFalse(game.isLegalMove(from, to));
    }

    // KING:
    // ========================================================
    @Test
    public void testIsLegalKingMoveUpDown() {
        Square oppositeTo = new Square(5, 4, null);
        from.setCoordinate(5,5);
        from.setPiece(whiteKing);
        to.setCoordinate(5,6);

        game.getBoard().updateBoardAt(5, 6, to);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(5, 4, oppositeTo);

        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingLeftRight() {
        Square oppositeTo = new Square(4, 5, null);
        from.setCoordinate(5,5);
        from.setPiece(whiteKing);
        to.setCoordinate(6,5);

        game.getBoard().updateBoardAt(6, 5, to);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(4, 5, oppositeTo);

        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingDiagonal() {
        Square upperRight = new Square(6, 6, null);
        Square upperLeft = new Square(4, 6, null);
        Square from = new Square(5,5, whiteKing);
        Square lowerRight = new Square(6, 4, null);
        Square lowerLeft = new Square(4, 4, null);

        game.getBoard().updateBoardAt(6, 6, upperRight);
        game.getBoard().updateBoardAt(4, 6, upperLeft);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(6,4,lowerRight);
        game.getBoard().updateBoardAt(4,4,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKingCaptureUpDown() {
        Square up = new Square(5,6, blackPawn);
        Square from = new Square(5,5, whiteKing);
        Square down = new Square(5, 4, blackPawn);

        game.getBoard().updateBoardAt(5, 6, up);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(5, 4, down);

        assertTrue(game.isLegalMove(from, up));
        assertTrue(game.isLegalMove(from, down));
    }

    @Test
    public void testIsLegalMoveKingCaptureLeftRight() {
        Square right = new Square(6,5, blackPawn);
        Square from = new Square(5,5, whiteKing);
        Square left = new Square(4, 5, blackPawn);

        game.getBoard().updateBoardAt(6, 5, right);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(4, 5, left);

        assertTrue(game.isLegalMove(from, right));
        assertTrue(game.isLegalMove(from, left));
    }

    @Test
    public void testIsLegalMoveKingCaptureDiagonal() {
        Square upperRight = new Square(6, 6, blackPawn);
        Square upperLeft = new Square(4, 6, blackPawn);
        Square from = new Square(5,5, whiteKing);
        Square lowerRight = new Square(6, 4, blackPawn);
        Square lowerLeft = new Square(4, 4, blackPawn);

        game.getBoard().updateBoardAt(6, 6, upperRight);
        game.getBoard().updateBoardAt(4, 6, upperLeft);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(6,4,lowerRight);
        game.getBoard().updateBoardAt(4,4,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }
}

