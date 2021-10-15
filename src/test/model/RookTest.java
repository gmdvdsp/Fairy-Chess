package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    Rook rook = new Rook("white");

    @Test
    public void testPrintPiece() {
        assertTrue(rook.printPiece().equals("R"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(rook.printColourOneCharacter().equals("w"));
        rook.setColour("black");
        assertTrue(rook.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(rook.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        rook.setColour("black");
        assertTrue(rook.getColour().equals("black"));
    }
}