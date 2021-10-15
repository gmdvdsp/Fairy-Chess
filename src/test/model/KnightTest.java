package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    Knight knight = new Knight("white");

    @Test
    public void testPrintPiece() {
        assertTrue(knight.printPiece().equals("N"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(knight.printColourOneCharacter().equals("w"));
        knight.setColour("black");
        assertTrue(knight.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(knight.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        knight.setColour("black");
        assertTrue(knight.getColour().equals("black"));
    }
}