package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static model.Board.MAX_X_COORDINATE;
import static model.Board.MAX_Y_COORDINATE;
import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    // Colours:
    String arbitraryColour = "white";
    String oppositeColour = "black";

    // Squares:
    Square to = new Square(0, 0, null);
    Square from = new Square(0, 0, null);

    // Pieces:
    Pawn whitePawn = new Pawn("white");
    Pawn blackPawn = new Pawn("black");
    King king = new King(arbitraryColour);
    Rook rook = new Rook(arbitraryColour);
    Bishop bishop = new Bishop(arbitraryColour);
    Knight knight = new Knight(arbitraryColour);
    Queen queen = new Queen(arbitraryColour);
    Princess princess = new Princess(arbitraryColour);
    Dragon dragon = new Dragon(arbitraryColour);

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
    // ================================================================================================================
    @Test
    public void testIsLegalMoveWhitePawnOneMove() {
        Square from = new Square(0, 0, whitePawn);
        Square to = new Square(0,1,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnWrongWay() {
        Square from = new Square(0, MAX_Y_COORDINATE, whitePawn);
        Square to = new Square(0, MAX_Y_COORDINATE - 1,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnLegalCapture() {
        Square upperRight = new Square(2, 2, blackPawn);
        Square from = new Square(1, 1, whitePawn);
        Square upperLeft = new Square(0, 2, blackPawn);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
    }

    @Test
    public void testIsLegalMoveWhitePawnIllegalCaptureOwnPiece() {
        Square upperRight = new Square(3, 3, whitePawn);
        Square from = new Square(2, 2, whitePawn);
        Square upperLeft = new Square(2, 3, whitePawn);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
    }

    @Test
    public void testIsLegalMoveWhitePawnFirstMoveTwoMove() {
        Square to = new Square(0,3,null);
        Square between = new Square(0,2,null);
        Square from = new Square(0, 1, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(from);
        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnFirstMoveTwoMoveBlocked() {
        Square to = new Square(0,3,null);
        Square between = new Square(0,2,whitePawn);
        Square from = new Square(0, 1, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(from);
        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnNotFirstMoveTwoMove() {
        Square to = new Square(5,4,null);
        Square between = new Square(5,3,null);
        Square from = new Square(5, 2, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(from);
        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveWhitePawnMoveIntoAlliedPiece() {
        Square to = new Square(6,3,whitePawn);
        Square between = new Square(6,2,whitePawn);
        Square from = new Square(6, 1, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(from);
        assertFalse(game.isLegalMove(from, to));
    }

    // Pawn (Black):
    // ================================================================================================================
    @Test
    public void testIsLegalMoveBlackPawnOneMove() {
        Square from = new Square(0, MAX_Y_COORDINATE - 1, blackPawn);
        Square to = new Square(0,MAX_Y_COORDINATE - 2,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnWrongWay() {
        Square from = new Square(6, 5, blackPawn);
        Square to = new Square(6,6,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnLegalCapture() {
        Square lowerRight = new Square(4, 5, whitePawn);
        Square from = new Square(5, 6, blackPawn);
        Square lowerLeft = new Square(4, 5, whitePawn);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveBackBlackPawnIllegalCaptureOwnPiece() {
        Square lowerRight = new Square(7, 6, blackPawn);
        Square from = new Square(6, 7, blackPawn);
        Square lowerLeft = new Square(5, 6, blackPawn);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerLeft);
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveBlackPawnFirstMoveTwoMove() {
        Square from = new Square(MAX_X_COORDINATE, MAX_Y_COORDINATE - 1, blackPawn);
        Square between = new Square(MAX_X_COORDINATE,MAX_Y_COORDINATE - 2,null);
        Square to = new Square(MAX_X_COORDINATE,MAX_Y_COORDINATE - 3,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(to);
        assertTrue(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnFirstMoveTwoMoveBlocked() {
        Square from = new Square(7, MAX_Y_COORDINATE - 1, blackPawn);
        Square between = new Square(7,MAX_Y_COORDINATE - 2,whitePawn);
        Square to = new Square(7,MAX_Y_COORDINATE - 3,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(to);
        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnNotFirstMoveTwoMove() {
        Square from = new Square(MAX_X_COORDINATE, MAX_Y_COORDINATE - 3, blackPawn);
        Square between = new Square(MAX_X_COORDINATE,MAX_Y_COORDINATE - 4,null);
        Square to = new Square(MAX_X_COORDINATE,MAX_Y_COORDINATE - 5,null);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(between);
        game.getBoard().replaceSquare(to);
        assertFalse(game.isLegalMove(from, to));
    }

    @Test
    public void testIsLegalMoveBlackPawnMoveIntoAlliedPiece() {
        Square from = new Square(0, MAX_Y_COORDINATE - 3, blackPawn);
        Square to = new Square(0,MAX_Y_COORDINATE - 4, blackPawn);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        assertFalse(game.isLegalMove(from, to));
    }

    // KING:
    // ================================================================================================================
    @Test
    public void testIsLegalKingMoveUpDown() {
        Square to = new Square(0,2, null);
        Square from = new Square(0,1, king);
        Square oppositeTo = new Square(0, 0, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingLeftRight() {
        Square to = new Square(MAX_X_COORDINATE,0, null);
        Square from = new Square(MAX_X_COORDINATE - 1, 0, king);
        Square oppositeTo = new Square(MAX_X_COORDINATE - 2, 0, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingDiagonal() {
        Square upperRight = new Square(6, 6, null);
        Square upperLeft = new Square(4, 6, null);
        Square from = new Square(5,5, king);
        Square lowerRight = new Square(6, 4, null);
        Square lowerLeft = new Square(4, 4, null);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKingCaptureUpDown() {
        Square to = new Square(2,5, blackPawn);
        Square from = new Square(2,4, king);
        Square oppositeTo = new Square(2, 3, blackPawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingCaptureLeftRight() {
        Square to = new Square(MAX_X_COORDINATE - 3,0, blackPawn);
        Square from = new Square(MAX_X_COORDINATE - 4, 0, king);
        Square oppositeTo = new Square(MAX_X_COORDINATE - 5, 0, blackPawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingCaptureDiagonal() {
        Square upperRight = new Square(3, 3, blackPawn);
        Square upperLeft = new Square(1, 3, blackPawn);
        Square from = new Square(2,2, king);
        Square lowerRight = new Square(3, 1, blackPawn);
        Square lowerLeft = new Square(1, 1, blackPawn);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKingUpDownMoveIntoAlliedPiece() {
        Square to = new Square(0,7, whitePawn);
        Square from = new Square(0,6, king);
        Square oppositeTo = new Square(0, 5, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingLeftRightMoveIntoAlliedPiece() {
        Square to = new Square(MAX_X_COORDINATE - 6,0, whitePawn);
        Square from = new Square(MAX_X_COORDINATE - 5, 0, king);
        Square oppositeTo = new Square(MAX_X_COORDINATE - 4, 0, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveKingDiagonalMoveIntoALliedPiece() {
        Square upperRight = new Square(7, 7, whitePawn);
        Square upperLeft = new Square(5, 7, whitePawn);
        Square from = new Square(6,6, king);
        Square lowerRight = new Square(7, 7, whitePawn);
        Square lowerLeft = new Square(5, 7, whitePawn);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    // ROOK:
    // ================================================================================================================
    @Test
    public void testIsLegalMoveRookUpDown() {
        Square to = new Square(0,MAX_Y_COORDINATE, null);
        Square from = new Square(0,5, rook);
        Square oppositeTo = new Square(0, 0, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookLeftRight() {
        Square to = new Square(MAX_X_COORDINATE, MAX_Y_COORDINATE, null);
        Square from = new Square(MAX_X_COORDINATE / 2, MAX_Y_COORDINATE, rook);
        Square oppositeTo = new Square(0, MAX_Y_COORDINATE, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookIllegal() {
        Square to = new Square(5,MAX_Y_COORDINATE - 1, null);
        Square from = new Square(0,5, rook);
        Square oppositeTo = new Square(7, 7, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookUpDownBlocked() {
        Square to = new Square(0,7, whitePawn);
        Square blockedTo = new Square(0, 6, whitePawn);
        Square from = new Square(0,4, rook);
        Square blockedOppositeTo = new Square(0, 2, whitePawn);
        Square oppositeTo = new Square(0, 0, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedTo);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedOppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookIllegalLeftRightBlocked() {
        Square to = new Square(MAX_X_COORDINATE ,4, whitePawn);
        Square blockedTo = new Square(MAX_X_COORDINATE - 1, 4, whitePawn);
        Square from = new Square(MAX_X_COORDINATE - 3,4, rook);
        Square blockedOppositeTo = new Square(MAX_X_COORDINATE - 5, 4, whitePawn);
        Square oppositeTo = new Square(0, 4, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedTo);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedOppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookCaptureUpDown() {
        Square to = new Square(3,MAX_Y_COORDINATE, blackPawn);
        Square from = new Square(3,5, rook);
        Square oppositeTo = new Square(3, 0, blackPawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookCaptureLeftRight() {
        Square to = new Square(MAX_X_COORDINATE, MAX_Y_COORDINATE, blackPawn);
        Square from = new Square(MAX_X_COORDINATE / 2, MAX_Y_COORDINATE, rook);
        Square oppositeTo = new Square(0, MAX_Y_COORDINATE, blackPawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookUpDownMoveIntoAlliedPiece() {
        Square to = new Square(7,MAX_Y_COORDINATE, rook);
        Square from = new Square(7,5, rook);
        Square oppositeTo = new Square(7, 0, rook);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveRookLeftRightMoveIntoAlliedPiece() {
        Square to = new Square(MAX_X_COORDINATE / 2 + 1, MAX_Y_COORDINATE, rook);
        Square from = new Square(MAX_X_COORDINATE / 2, MAX_Y_COORDINATE, rook);
        Square oppositeTo = new Square(0, MAX_Y_COORDINATE / 2 - 1, rook);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    // BISHOP:
    // ================================================================================================================
    @Test
    public void testIsLegalMoveBishopUpperRightLowerLeft() {
        Square upperRight = new Square(8, 7, null);
        Square from = new Square(3,2, bishop);
        Square lowerLeft = new Square(1, 0, null);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveBishopUpperLeftLowerRight() {
        Square upperLeft = new Square(0, 0, null);
        Square from = new Square(1,1, bishop);
        Square lowerRight = new Square(2, 0, null);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
    }

    @Test
    public void testIsLegalMoveBishopIllegal() {
        Square to = new Square(0, MAX_Y_COORDINATE, null);
        Square from = new Square(5,5, bishop);
        Square oppositeTo = new Square(0, 5, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveBishopIllegalUpperRightLowerLeftBlocked() {
        Square lowerLeft = new Square(0,0, blackPawn);
        Square lowerLeftBlock = new Square(1, 1, blackPawn);
        Square from = new Square(5, 5, bishop);
        Square upperRightBlock = new Square(6, 6, blackPawn);
        Square upperRight = new Square(7, 7, blackPawn);
        game.getBoard().replaceSquare(lowerLeft);
        game.getBoard().replaceSquare(lowerLeftBlock);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperRightBlock);
        game.getBoard().replaceSquare(upperRight);
        assertFalse(game.isLegalMove(from, lowerLeft));
        assertFalse(game.isLegalMove(from, upperRight));
    }

    @Test
    public void testIsLegalMoveBishopIllegalUpperLeftLowerRightBlocked() {
        Square lowerRight = new Square(5,3, blackPawn);
        Square lowerRightBlock = new Square(3, 4, blackPawn);
        Square from = new Square(2, 5, bishop);
        Square upperLeftBlock = new Square(1, 6, blackPawn);
        Square upperLeft = new Square(0, 7, blackPawn);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerRightBlock);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperLeftBlock);
        game.getBoard().replaceSquare(upperLeft);
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, upperLeft));
    }

    @Test
    public void testIsLegalMoveBishopUpperRightLowerLeftMoveIntoAlliedPiece() {
        Square upperRight = new Square(7, 7, dragon);
        Square from = new Square(5,4, bishop);
        Square lowerLeft = new Square(1, 0, dragon);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveBishopUpperLeftLowerRightMoveIntoAlliedPiece() {
        Square upperRight = new Square(0, 7, dragon);
        Square from = new Square(1,6, bishop);
        Square lowerLeft = new Square(7, 0, dragon);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    // KNIGHT:
    // ================================================================================================================
    @Test
    public void testIsLegalMoveKnightUpDown() {
        Square upperRight = new Square(4, 4, null);
        Square upperLeft = new Square(2, 4, null);
        Square from = new Square(3,2, knight);
        Square lowerRight = new Square(4, 0, null);
        Square lowerLeft = new Square(2, 0, null);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKnightLeftRight() {
        Square upperRight = new Square(6, 6, null);
        Square upperLeft = new Square(2, 6, null);
        Square from = new Square(4,5, knight);
        Square lowerRight = new Square(6, 4, null);
        Square lowerLeft = new Square(6, 4, null);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKnightIllegal() {
        Square upperRight = new Square(8, 7, null);
        Square upperLeft = new Square(0, 7, null);
        Square from = new Square(3,2, knight);
        Square lowerRight = new Square(6, 0, null);
        Square lowerLeft = new Square(1, 0, null);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKnightLegalCaptureUpDown() {
        Square upperRight = new Square(4, 4, blackPawn);
        Square upperLeft = new Square(2, 4, blackPawn);
        Square from = new Square(3,2, knight);
        Square lowerRight = new Square(4, 0, blackPawn);
        Square lowerLeft = new Square(2, 0, blackPawn);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKnightLegalCaptureLeftRight() {
        Square upperRight = new Square(5, 3, blackPawn);
        Square upperLeft = new Square(1, 3, blackPawn);
        Square from = new Square(3,2, knight);
        Square lowerRight = new Square(5, 1, blackPawn);
        Square lowerLeft = new Square(1, 1, blackPawn);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKnightUpDownMoveIntoAlliedPiece() {
        Square upperRight = new Square(4, 4, whitePawn);
        Square upperLeft = new Square(2, 4, whitePawn);
        Square from = new Square(3,2, knight);
        Square lowerRight = new Square(4, 0, whitePawn);
        Square lowerLeft = new Square(2, 0, whitePawn);
        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveKnightLeftRightMoveIntoAlliedPiece() {
        Square upperRight = new Square(5, 3, bishop);
        Square upperLeft = new Square(1, 3, knight);
        Square from = new Square(3,2, knight);
        Square lowerRight = new Square(5, 1, dragon);
        Square lowerLeft = new Square(1, 1, king);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerLeft);
        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    // QUEEN:
    // ================================================================================================================
    @Test
    public void testIsLegalMoveQueenBishopUpperRightLowerLeft() {
        Square upperRight = new Square(3, 6, null);
        Square from = new Square(1,4, queen);
        Square lowerLeft = new Square(0, 3, null);
        game.getBoard().replaceSquare(upperRight);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerLeft);
        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveQueenBishopUpperLeftLowerRight() {
        Square upperLeft = new Square(MAX_X_COORDINATE - 2, 2, null);
        Square from = new Square(MAX_X_COORDINATE - 1,1, bishop);
        Square lowerRight = new Square(MAX_X_COORDINATE, 0, null);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
    }

    @Test
    public void testIsLegalMoveQueenIllegal() {
        Square upperLeft = new Square(0, 2, null);
        Square from = new Square(MAX_X_COORDINATE - 1,1, bishop);
        Square upperRight = new Square(MAX_X_COORDINATE, MAX_Y_COORDINATE, null);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperRight);
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, upperRight));
    }

    @Test
    public void testIsLegalMoveQueenBishopUpperRightLowerLeftBlocked() {
        Square lowerLeft = new Square(0,0, blackPawn);
        Square lowerLeftBlock = new Square(2, 2, blackPawn);
        Square from = new Square(4, 4, queen);
        Square upperRightBlock = new Square(5, 5, blackPawn);
        Square upperRight = new Square(7, 7, blackPawn);
        game.getBoard().replaceSquare(lowerLeft);
        game.getBoard().replaceSquare(lowerLeftBlock);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperRightBlock);
        game.getBoard().replaceSquare(upperRight);
        assertFalse(game.isLegalMove(from, lowerLeft));
        assertFalse(game.isLegalMove(from, upperRight));
    }

    @Test
    public void testIsLegalMoveQueenBishopUpperLeftLowerRightBlocked() {
        Square lowerRight = new Square(5,3, blackPawn);
        Square lowerRightBlock = new Square(3, 4, blackPawn);
        Square from = new Square(2, 5, queen);
        Square upperLeftBlock = new Square(1, 6, blackPawn);
        Square upperLeft = new Square(0, 7, blackPawn);
        game.getBoard().replaceSquare(lowerRight);
        game.getBoard().replaceSquare(lowerRightBlock);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(upperLeftBlock);
        game.getBoard().replaceSquare(upperLeft);
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, upperLeft));
    }

    @Test
    public void testIsLegalMoveQueenBishopUpperRightLowerLeftMoveIntoAlliedPiece() {
        Square top = new Square(8,7, whitePawn);
        Square from = new Square(3,2, queen);
        Square down = new Square(1, 0, whitePawn);
        game.getBoard().updateBoardAt(8, 7, top);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(1, 0, down);
        assertFalse(game.isLegalMove(from, top));
        assertFalse(game.isLegalMove(from, down));
    }

    @Test
    public void testIsLegalMoveQueenBishopUpperLeftLowerRightMoveIntoAlliedPiece() {
        Square upperLeft = new Square(5, 6, king);
        Square from = new Square(6,5, queen);
        Square lowerRight = new Square(8, 3, princess);
        game.getBoard().replaceSquare(upperLeft);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(lowerRight);
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        }

    @Test
    public void testIsLegalMoveQueenRookUpDown() {
        Square to = new Square(8,MAX_Y_COORDINATE - 3, null);
        Square from = new Square(8,5, queen);
        Square oppositeTo = new Square(8, 1, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookLeftRight() {
        Square to = new Square(MAX_X_COORDINATE, MAX_Y_COORDINATE, null);
        Square from = new Square(MAX_X_COORDINATE / 2 + 1, MAX_Y_COORDINATE, queen);
        Square oppositeTo = new Square(0, MAX_Y_COORDINATE, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookUpDownBlocked() {
        Square to = new Square(1,7, null);
        Square blockedTo = new Square(1, 6, whitePawn);
        Square from = new Square(1,4, rook);
        Square blockedOppositeTo = new Square(1, 2, whitePawn);
        Square oppositeTo = new Square(1, 0, null);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedTo);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedOppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookLeftRightBlocked() {
        Square to = new Square(MAX_X_COORDINATE ,4, whitePawn);
        Square blockedTo = new Square(MAX_X_COORDINATE - 2, 4, whitePawn);
        Square from = new Square(MAX_X_COORDINATE - 4,4, queen);
        Square blockedOppositeTo = new Square(MAX_X_COORDINATE - 5, 4, whitePawn);
        Square oppositeTo = new Square(0, 4, whitePawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedTo);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(blockedOppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookCaptureUpDown() {
        Square to = new Square(MAX_X_COORDINATE,7, blackPawn);
        Square from = new Square(MAX_X_COORDINATE,5, queen);
        Square oppositeTo = new Square(MAX_X_COORDINATE, 4, blackPawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookCaptureLeftRight() {
        Square to = new Square(MAX_X_COORDINATE - 1, MAX_Y_COORDINATE, blackPawn);
        Square from = new Square(MAX_X_COORDINATE / 2 + 2, MAX_Y_COORDINATE, rook);
        Square oppositeTo = new Square(1, MAX_Y_COORDINATE, blackPawn);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookUpDownMoveIntoAlliedPiece() {
        Square to = new Square(6,MAX_Y_COORDINATE, rook);
        Square from = new Square(6,5, queen);
        Square oppositeTo = new Square(6, 0, rook);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMoveQueenRookLeftRightMoveIntoAlliedPiece() {
        Square to = new Square(MAX_X_COORDINATE / 2, MAX_Y_COORDINATE, rook);
        Square from = new Square(MAX_X_COORDINATE / 2 - 3, MAX_Y_COORDINATE, queen);
        Square oppositeTo = new Square(2, MAX_Y_COORDINATE / 2 - 1, rook);
        game.getBoard().replaceSquare(to);
        game.getBoard().replaceSquare(from);
        game.getBoard().replaceSquare(oppositeTo);
        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    // PRINCESS:
    // ================================================================================================================
    @Test
    public void testIsLegalMovePrincessUpDown() {
        Square oppositeTo = new Square(5, 0, null);
        from.setCoordinate(5,5);
        from.setPiece(princess);
        to.setCoordinate(5,7);

        game.getBoard().updateBoardAt(5, 7, to);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(5, 0, oppositeTo);

        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMovePrincessLeftRight() {
        Square oppositeTo = new Square(7, 5, null);
        from.setCoordinate(5,5);
        from.setPiece(princess);
        to.setCoordinate(7,5);

        game.getBoard().updateBoardAt(0, 5, to);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(7, 5, oppositeTo);

        assertTrue(game.isLegalMove(from, to));
        assertTrue(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMovePrincessIllegalUpDownBlocked() {
        Square oppositeTo = new Square(5, 0, blackPawn);
        Square blockedOppositeTo = new Square(5, 3, blackPawn);
        Square blockedTo = new Square(5, 6, blackPawn);
        from.setCoordinate(5,5);
        from.setPiece(queen);
        to.setCoordinate(5,7);

        game.getBoard().updateBoardAt(5, 7, to);
        game.getBoard().updateBoardAt(5, 6, blockedTo);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(5, 3, blockedOppositeTo);
        game.getBoard().updateBoardAt(5, 0, oppositeTo);

        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMovePrincessIllegalLeftRightBlocked() {
        Square oppositeTo = new Square(0, 5, blackPawn);
        Square blockedOppositeTo = new Square(3, 5, blackPawn);
        Square blockedTo = new Square(7, 5, blackPawn);
        from.setCoordinate(5,5);
        from.setPiece(princess);
        to.setCoordinate(8,5);

        game.getBoard().updateBoardAt(8, 5, to);
        game.getBoard().updateBoardAt(7, 5, blockedTo);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(3, 5, blockedOppositeTo);
        game.getBoard().updateBoardAt(0, 5, oppositeTo);

        assertFalse(game.isLegalMove(from, to));
        assertFalse(game.isLegalMove(from, oppositeTo));
    }

    @Test
    public void testIsLegalMovePrincessCaptureUpDown() {
        Square top = new Square(5,7, blackPawn);
        Square from = new Square(5,5, princess);
        Square down = new Square(5, 0, blackPawn);

        game.getBoard().updateBoardAt(5, 7, top);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(5, 0, down);

        assertTrue(game.isLegalMove(from, top));
        assertTrue(game.isLegalMove(from, down));
    }

    @Test
    public void testIsLegalMovePrincessCaptureLeftRight() {
        Square right = new Square(8,5, blackPawn);
        Square from = new Square(5,5, princess);
        Square left = new Square(0, 5, blackPawn);

        game.getBoard().updateBoardAt(8, 5, right);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(0, 5, left);

        assertTrue(game.isLegalMove(from, right));
        assertTrue(game.isLegalMove(from, left));
    }

    @Test
    public void testIsLegalMovePrincessUpDownMoveIntoAlliedPiece() {
        Square top = new Square(5,7, whitePawn);
        Square from = new Square(5,5, princess);
        Square down = new Square(5, 0, whitePawn);

        game.getBoard().updateBoardAt(5, 7, top);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(5, 0, down);

        assertFalse(game.isLegalMove(from, top));
        assertFalse(game.isLegalMove(from, down));
    }

    @Test
    public void testIsLegalMovePrincessLeftRightMoveIntoAlliedPiece() {
        Square right = new Square(8,5, whitePawn);
        Square from = new Square(5,5, princess);
        Square left = new Square(0, 5, whitePawn);

        game.getBoard().updateBoardAt(8, 5, right);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(0, 5, left);

        assertFalse(game.isLegalMove(from, right));
        assertFalse(game.isLegalMove(from, left));
    }

    @Test
    public void testIsLegalMovePrincessKnightUpDown() {
        Square upperRight = new Square(4, 4, null);
        Square upperLeft = new Square(2, 4, null);
        Square from = new Square(3,2, princess);
        Square lowerRight = new Square(4, 0, null);
        Square lowerLeft = new Square(2, 0, null);

        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMovePrincessKnightLeftRight() {
        Square upperRight = new Square(5, 3, null);
        Square upperLeft = new Square(1, 3, null);
        Square from = new Square(3,2, princess);
        Square lowerRight = new Square(5, 1, null);
        Square lowerLeft = new Square(1, 1, null);

        game.getBoard().updateBoardAt(5, 3, upperRight);
        game.getBoard().updateBoardAt(1, 3, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(5, 1, lowerRight);
        game.getBoard().updateBoardAt(1,1,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMovePrincessKnightLegalCaptureUpDown() {
        Square upperRight = new Square(4, 4, blackPawn);
        Square upperLeft = new Square(2, 4, blackPawn);
        Square from = new Square(3,2, princess);
        Square lowerRight = new Square(4, 0, blackPawn);
        Square lowerLeft = new Square(2, 0, blackPawn);

        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMovePrincessKnightLegalCaptureLeftRight() {
        Square upperRight = new Square(5, 3, blackPawn);
        Square upperLeft = new Square(1, 3, blackPawn);
        Square from = new Square(3,2, princess);
        Square lowerRight = new Square(5, 1, blackPawn);
        Square lowerLeft = new Square(1, 1, blackPawn);

        game.getBoard().updateBoardAt(5, 3, upperRight);
        game.getBoard().updateBoardAt(1, 3, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(5, 1, lowerRight);
        game.getBoard().updateBoardAt(1,1,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMovePrincessKnightUpDownMoveIntoAlliedPiece() {
        Square upperRight = new Square(4, 4, whitePawn);
        Square upperLeft = new Square(2, 4, whitePawn);
        Square from = new Square(3,2, princess);
        Square lowerRight = new Square(4, 0, whitePawn);
        Square lowerLeft = new Square(2, 0, whitePawn);

        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);

        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMovePrincessKnightLeftRightMoveIntoAlliedPiece() {
        Square upperRight = new Square(5, 3, whitePawn);
        Square upperLeft = new Square(1, 3, whitePawn);
        Square from = new Square(3,2, princess);
        Square lowerRight = new Square(5, 1, whitePawn);
        Square lowerLeft = new Square(1, 1, whitePawn);

        game.getBoard().updateBoardAt(5, 3, upperRight);
        game.getBoard().updateBoardAt(1, 3, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(5, 1, lowerRight);
        game.getBoard().updateBoardAt(1,1,lowerLeft);

        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    // DRAGON:
    // ================================================================================================================
    @Test
    public void testIsLegalMoveDragonKnightUpDown() {
        Square upperRight = new Square(4, 4, null);
        Square upperLeft = new Square(2, 4, null);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(4, 0, null);
        Square lowerLeft = new Square(2, 0, null);

        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonKnightLeftRight() {
        Square upperRight = new Square(5, 3, null);
        Square upperLeft = new Square(1, 3, null);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(5, 1, null);
        Square lowerLeft = new Square(1, 1, null);

        game.getBoard().updateBoardAt(5, 3, upperRight);
        game.getBoard().updateBoardAt(1, 3, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(5, 1, lowerRight);
        game.getBoard().updateBoardAt(1,1,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonIllegal() {
        Square upperRight = new Square(9, 7, null);
        Square upperLeft = new Square(2, 7, null);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(6, 0, null);
        Square lowerLeft = new Square(0, 0, null);

        game.getBoard().updateBoardAt(9, 7, upperRight);
        game.getBoard().updateBoardAt(2, 7, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(6, 0, lowerRight);
        game.getBoard().updateBoardAt(0,0,lowerLeft);

        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonKnightLegalCaptureUpDown() {
        Square upperRight = new Square(4, 4, blackPawn);
        Square upperLeft = new Square(2, 4, blackPawn);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(4, 0, blackPawn);
        Square lowerLeft = new Square(2, 0, blackPawn);

        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonKnightLegalCaptureLeftRight() {
        Square upperRight = new Square(5, 3, blackPawn);
        Square upperLeft = new Square(1, 3, blackPawn);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(5, 1, blackPawn);
        Square lowerLeft = new Square(1, 1, blackPawn);

        game.getBoard().updateBoardAt(5, 3, upperRight);
        game.getBoard().updateBoardAt(1, 3, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(5, 1, lowerRight);
        game.getBoard().updateBoardAt(1,1,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonKnightUpDownMoveIntoAlliedPiece() {
        Square upperRight = new Square(4, 4, whitePawn);
        Square upperLeft = new Square(2, 4, whitePawn);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(4, 0, whitePawn);
        Square lowerLeft = new Square(2, 0, whitePawn);

        game.getBoard().updateBoardAt(4, 4, upperRight);
        game.getBoard().updateBoardAt(2, 4, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(4, 0, lowerRight);
        game.getBoard().updateBoardAt(2,0,lowerLeft);

        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonKnightLeftRightMoveIntoAlliedPiece() {
        Square upperRight = new Square(5, 3, whitePawn);
        Square upperLeft = new Square(1, 3, whitePawn);
        Square from = new Square(3,2, dragon);
        Square lowerRight = new Square(5, 1, whitePawn);
        Square lowerLeft = new Square(1, 1, whitePawn);

        game.getBoard().updateBoardAt(5, 3, upperRight);
        game.getBoard().updateBoardAt(1, 3, upperLeft);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(5, 1, lowerRight);
        game.getBoard().updateBoardAt(1,1,lowerLeft);

        assertFalse(game.isLegalMove(from, upperRight));
        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonBishopUpperRightLowerLeft() {
        Square upperRight = new Square(8, 7, null);
        Square from = new Square(3,2, dragon);
        Square lowerLeft = new Square(1, 0, null);

        game.getBoard().updateBoardAt(8, 7, upperRight);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(1,0,lowerLeft);

        assertTrue(game.isLegalMove(from, upperRight));
        assertTrue(game.isLegalMove(from, lowerLeft));
    }

    @Test
    public void testIsLegalMoveDragonBishopUpperLeftLowerRight() {
        Square upperLeft = new Square(3, 7, null);
        Square from = new Square(5,5, dragon);
        Square lowerRight = new Square(8, 2, null);

        game.getBoard().updateBoardAt(3, 7, upperLeft);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(8,2,lowerRight);

        assertTrue(game.isLegalMove(from, upperLeft));
        assertTrue(game.isLegalMove(from, lowerRight));
    }

    @Test
    public void testIsLegalMoveDragonBishopIllegalUpperRightLowerLeftBlocked() {
        Square lowerLeft = new Square(0,0, blackPawn);
        Square lowerLeftBlock = new Square(1, 1, blackPawn);
        Square from = new Square(5, 5, dragon);
        Square upperRightBlock = new Square(6, 6, blackPawn);
        Square upperRight = new Square(7, 7, blackPawn);

        game.getBoard().updateBoardAt(0, 0, lowerLeft);
        game.getBoard().updateBoardAt(1, 1, lowerLeftBlock);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(6, 6, upperRightBlock);
        game.getBoard().updateBoardAt(7, 7, upperRight);

        assertFalse(game.isLegalMove(from, lowerLeft));
        assertFalse(game.isLegalMove(from, upperRight));
    }

    @Test
    public void testIsLegalMoveDragonBishopIllegalUpperLeftLowerRightBlocked() {
        Square lowerRight = new Square(8,2, blackPawn);
        Square lowerRightBlock = new Square(7, 3, blackPawn);
        Square from = new Square(5, 5, dragon);
        Square upperLeftBlock = new Square(4, 6, blackPawn);
        Square upperLeft = new Square(3, 7, blackPawn);

        game.getBoard().updateBoardAt(8, 2, lowerRight);
        game.getBoard().updateBoardAt(7, 3, lowerRightBlock);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(4, 6, upperLeftBlock);
        game.getBoard().updateBoardAt(3, 7, upperLeft);

        assertFalse(game.isLegalMove(from, lowerRight));
        assertFalse(game.isLegalMove(from, upperLeft));
    }

    @Test
    public void testIsLegalMoveDragonBishopUpperRightLowerLeftMoveIntoAlliedPiece() {
        Square top = new Square(8,7, whitePawn);
        Square from = new Square(3,2, dragon);
        Square down = new Square(1, 0, whitePawn);

        game.getBoard().updateBoardAt(8, 7, top);
        game.getBoard().updateBoardAt(3, 2, from);
        game.getBoard().updateBoardAt(1, 0, down);

        assertFalse(game.isLegalMove(from, top));
        assertFalse(game.isLegalMove(from, down));
    }

    @Test
    public void testIsLegalMoveDragonBishopUpperLeftLowerRightMoveIntoAlliedPiece() {
        Square upperLeft = new Square(3, 7, whitePawn);
        Square from = new Square(5,5, dragon);
        Square lowerRight = new Square(8, 2, whitePawn);

        game.getBoard().updateBoardAt(3, 7, upperLeft);
        game.getBoard().updateBoardAt(5, 5, from);
        game.getBoard().updateBoardAt(8,2,lowerRight);

        assertFalse(game.isLegalMove(from, upperLeft));
        assertFalse(game.isLegalMove(from, lowerRight));
    }
}

