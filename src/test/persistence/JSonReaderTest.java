package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// From CPSC-210 given persistence example code:
public class JSonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JSonReader reader = new JSonReader("./data/noSuchFile.json");
        try {
            Game g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGame() {
        JSonReader reader = new JSonReader("./data/testReaderEmptyGame.json");
        try {
            Game g = reader.read();
            for (int y = 0; y <= g.getBoard().getYmax(); y++) {
                for (int x = 0; x <= g.getBoard().getXmax(); x++) {
                    assertTrue(g.getBoard().getSquareAt(x, y).getIsEmpty());
                }
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGameBlackToPlay() {
        JSonReader reader = new JSonReader("./data/testReaderGameBlackToPlay.json");
        try {
            Game g = reader.read();
            assertEquals(g.getCapturedPieces().size(), 1);
            assertTrue(g.getCapturedPieces().get(0).getColour().equals("white"));
            assertTrue(g.getCapturedPieces().get(0).getClass().getSimpleName().equals("Pawn"));
            assertTrue(g.getCurrentTurn().equals("black"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGameBlackToPlayBlackPiecesCorrect() {
        JSonReader reader = new JSonReader("./data/testReaderGameBlackToPlay.json");
        try {
            Game g = reader.read();
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
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGameBlackToPlayWhitePiecesCorrect() {
        JSonReader reader = new JSonReader("./data/testReaderGameBlackToPlay.json");
        try {
            Game g = reader.read();
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
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
