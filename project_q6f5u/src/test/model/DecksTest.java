package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Decks
public class DecksTest {
    Decks testDecks;

    @BeforeEach
    public void setup() {
        testDecks = new Decks();
    }

    @Test
    public void testEmptyDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
    }

    @Test
    public void testAddEmptyDeckToEmptyDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
        testDecks.createDeck("deck1");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals(null, testDecks.getDeck("deck1").getHead());
        assertEquals(null, testDecks.getDeck("deck1").getTail());
        assertEquals(null, testDecks.getDeck("deck1").getCurrentlyViewing());
        assertEquals(0, testDecks.getDeck("deck1").getCardCounter());
        assertEquals("deck1", testDecks.getDeck("deck1").getDeckName());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals(1, testDecks.getDeckNames().size());
        assertTrue(testDecks.checkDecksContains("deck1"));
        assertEquals("deck1" + "\n", testDecks.listDeckNames());
    }

    @Test
    public void testAddDeckToNonEmptyDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
        testDecks.createDeck("deck1");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals(1, testDecks.getDeckNames().size());
        testDecks.createDeck("deck2");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals("deck2", testDecks.getDeckNames().get(1));
        assertEquals(2, testDecks.getDeckNames().size());
        assertTrue(testDecks.checkDecksContains("deck1"));
        assertTrue(testDecks.checkDecksContains("deck2"));
        assertEquals("deck1" + "\n" + "deck2" + "\n", testDecks.listDeckNames());
    }

    @Test
    public void testRemoveDeckFromEmptyDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
        testDecks.removeDeck("deck1");
        assertTrue(testDecks.getDeckNames().isEmpty());
        assertEquals(0, testDecks.getDeckNames().size());
        assertFalse(testDecks.checkDecksContains("deck1"));
        assertEquals("", testDecks.listDeckNames());
    }

    @Test
    public void testRemoveDeckNotInDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
        testDecks.createDeck("deck1");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals(1, testDecks.getDeckNames().size());
        testDecks.removeDeck("deck2");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals(1, testDecks.getDeckNames().size());
        assertTrue(testDecks.checkDecksContains("deck1"));
        assertFalse(testDecks.checkDecksContains("deck2"));
        assertEquals("deck1" + "\n", testDecks.listDeckNames());
    }

    @Test
    public void testRemoveDeckInDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
        testDecks.createDeck("deck1");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals(1, testDecks.getDeckNames().size());
        testDecks.removeDeck("deck1");
        assertTrue(testDecks.getDeckNames().isEmpty());
        assertEquals(0, testDecks.getDeckNames().size());
        assertFalse(testDecks.checkDecksContains("deck1"));
        assertEquals("", testDecks.listDeckNames());
    }

    @Test
    public void testRemoveTwoDeckInDecks() {
        assertTrue(testDecks.getDeckNames().isEmpty());
        testDecks.createDeck("deck1");
        testDecks.createDeck("deck2");
        testDecks.createDeck("deck3");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck1", testDecks.getDeckNames().get(0));
        assertEquals("deck2", testDecks.getDeckNames().get(1));
        assertEquals("deck3", testDecks.getDeckNames().get(2));
        assertEquals(3, testDecks.getDeckNames().size());
        testDecks.removeDeck("deck1");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck2", testDecks.getDeckNames().get(0));
        assertEquals("deck3", testDecks.getDeckNames().get(1));
        assertEquals(2, testDecks.getDeckNames().size());
        testDecks.removeDeck("deck2");
        assertFalse(testDecks.getDeckNames().isEmpty());
        assertEquals("deck3", testDecks.getDeckNames().get(0));
        assertEquals(1, testDecks.getDeckNames().size());
        assertFalse(testDecks.checkDecksContains("deck1"));
        assertFalse(testDecks.checkDecksContains("deck2"));
        assertTrue(testDecks.checkDecksContains("deck3"));
        assertEquals("deck3" + "\n", testDecks.listDeckNames());
    }

}
