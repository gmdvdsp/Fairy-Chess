package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    Bishop bishop = new Bishop("white");

    @Test
    public void testPrintPiece() {
        assertTrue(bishop.printPiece().equals("B"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(bishop.printColourOneCharacter().equals("w"));
        bishop.setColour("black");
        assertTrue(bishop.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(bishop.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        bishop.setColour("black");
        assertTrue(bishop.getColour().equals("black"));
    }
}