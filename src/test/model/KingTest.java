package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {
    King king = new King("white");

    @Test
    public void testPrintPiece() {
        assertTrue(king.printPiece().equals("K"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(king.printColourOneCharacter().equals("w"));
        king.setColour("black");
        assertTrue(king.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(king.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        king.setColour("black");
        assertTrue(king.getColour().equals("black"));
    }
}
