package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    Pawn pawn = new Pawn("white");

    @Test
    public void testPrintPiece() {
        assertTrue(pawn.printPiece().equals("P"));
    }

    @Test
    public void testPrintColourOneCharacterWhite() {
        assertTrue(pawn.printColourOneCharacter().equals("w"));
        pawn.setColour("black");
        assertTrue(pawn.printColourOneCharacter().equals("b"));
    }

    @Test
    public void testGetColour() {
        assertEquals(pawn.getColour(), "white");
    }

    @Test
    public void testSetColour() {
        pawn.setColour("black");
        assertTrue(pawn.getColour().equals("black"));
    }
}