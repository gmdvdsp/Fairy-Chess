package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrincessTest {
    Princess princess = new Princess("white");

    @Test
    public void testPrintPiece() {
        assertTrue(princess.printPiece().equals("I"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(princess.printColourOneCharacter().equals("w"));
        princess.setColour("black");
        assertTrue(princess.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(princess.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        princess.setColour("black");
        assertTrue(princess.getColour().equals("black"));
    }
}