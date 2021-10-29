package persistence;

import model.Game;
import model.Pawn;
import model.Square;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// From CPSC-210 given persistence example code:
class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Game g = new Game();
            JSonWriter writer = new JSonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGame() {
        try {
            Game g = new Game();
            JSonWriter writer = new JSonWriter("./data/testWriterEmptyGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JSonReader reader = new JSonReader("./data/testWriterEmptyGame.json");
            g = reader.read();
            for (Square square : g.getBoard().getSquareList()) {
                assertTrue(square.getIsEmpty());
            }
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGameWhiteToPlay() {
        try {
            Game g = new Game();
            g.getBoard().defaultBoard();
            g.getCapturedPieces().add(new Pawn("white"));
            JSonWriter writer = new JSonWriter("./data/testWriterGameWhiteToPlay.json");
            writer.open();
            writer.write(g);
            writer.close();

            JSonReader reader = new JSonReader("./data/testWriterGameWhiteToPlay.json");
            g = reader.read();
            blackPiecesCorrect(g);
            whitePiecesCorrect(g);
            assertTrue(g.getCapturedPieces().get(0).getColour().equals("white"));
            assertTrue(g.getCapturedPieces().get(0).getClass().getSimpleName().equals("Pawn"));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void blackPiecesCorrect(Game g) {
        for (int y = 6; y <= g.getBoard().getYmax(); y++) {
            for (int x = 0; x <= g.getBoard().getXmax(); x++) {
                assertTrue(g.getBoard().getSquareAt(x,y).getPieceOnSquare().getColour().equals("black"));
            }
        }
        for (int x = 0; x <= g.getBoard().getXmax(); x++) {
            assertTrue(g.getBoard().getSquareAt(x, 6).getPieceOnSquare().getClass().getSimpleName().
                    equals("Pawn"));
        }
        assertTrue(g.getBoard().getSquareAt(0, 7).getPieceOnSquare().getClass().getSimpleName().
                equals("Dragon"));
        assertTrue(g.getBoard().getSquareAt(9, 7).getPieceOnSquare().getClass().getSimpleName().
                equals("Princess"));
        for (int x = 1; x < g.getBoard().getXmax(); x += 7) {
            assertTrue(g.getBoard().getSquareAt(x, 7).getPieceOnSquare().getClass().getSimpleName().
                    equals("Rook"));
        }
        for (int x = 2; x < g.getBoard().getXmax(); x += 5) {
            assertTrue(g.getBoard().getSquareAt(x, 7).getPieceOnSquare().getClass().getSimpleName().
                    equals("Knight"));
        }
        for (int x = 3; x < g.getBoard().getXmax(); x += 3) {
            assertTrue(g.getBoard().getSquareAt(x, 7).getPieceOnSquare().getClass().getSimpleName().
                    equals("Bishop"));
        }
    }

    void whitePiecesCorrect(Game g) {
        for (int y = 0; y <= 1; y++) {
            for (int x = 0; x <= g.getBoard().getXmax(); x++) {
                assertTrue(g.getBoard().getSquareAt(x,y).getPieceOnSquare().getColour().equals("white"));
            }
        }
        for (int x = 0; x <= g.getBoard().getXmax(); x++) {
            assertTrue(g.getBoard().getSquareAt(x, 1).getPieceOnSquare().getClass().getSimpleName().
                    equals("Pawn"));
        }
        assertTrue(g.getBoard().getSquareAt(0, 0).getPieceOnSquare().getClass().getSimpleName().
                equals("Dragon"));
        assertTrue(g.getBoard().getSquareAt(9, 0).getPieceOnSquare().getClass().getSimpleName().
                equals("Princess"));
        for (int x = 1; x < g.getBoard().getXmax(); x += 7) {
            assertTrue(g.getBoard().getSquareAt(x, 0).getPieceOnSquare().getClass().getSimpleName().
                    equals("Rook"));
        }
        for (int x = 2; x < g.getBoard().getXmax(); x += 5) {
            assertTrue(g.getBoard().getSquareAt(x, 0).getPieceOnSquare().getClass().getSimpleName().
                    equals("Knight"));
        }
        for (int x = 3; x < g.getBoard().getXmax(); x += 3) {
            assertTrue(g.getBoard().getSquareAt(x, 0).getPieceOnSquare().getClass().getSimpleName().
                    equals("Bishop"));
        }
    }
}