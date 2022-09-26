package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Deck
public class DeckTest {
    Card testCard1;
    Card testCard2;
    Card testCard3;
    DeckNode testDeckNode1;
    DeckNode testDeckNode2;
    DeckNode testDeckNode3;
    Deck testDeck1;

    @BeforeEach
    public void setup() {
        testCard1 = new Card("1+1", "2");
        testCard2 = new Card("2+2", "4");
        testCard3 = new Card("3+3", "6");
        testDeckNode1 = new DeckNode(testCard1);
        testDeckNode2 = new DeckNode(testCard2);
        testDeckNode3 = new DeckNode(testCard3);
        testDeck1 = new Deck("deck1");
    }

    @Test
    public void testEmptyDeck() {
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
    }

    @Test
    public void testAddCardToEmptyDeck() {
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
        testDeck1.addDeckNode(testDeckNode1);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode1, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(1, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
    }

    @Test
    public void testAddCardsToNonEmptyDeck() {
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        testDeck1.addDeckNode(testDeckNode1);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode1, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(1, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
        testDeck1.addDeckNode(testDeckNode2);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode2, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(2, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode2, testDeck1.getTail());
        assertEquals(testDeckNode2, testDeck1.getHead().getNextDeckNode());
        assertEquals(testDeckNode2, testDeck1.getHead().getPrevDeckNode());
        assertEquals(testDeckNode1, testDeck1.getTail().getNextDeckNode());
        assertEquals(testDeckNode1, testDeck1.getTail().getPrevDeckNode());
    }

    @Test
    public void testDeleteCardFromEmptyDeck() {
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
        testDeck1.deleteDeckNode(testDeckNode1);
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
    }

    @Test
    public void testDeleteCardFromDeckWithOneCard() {
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
        testDeck1.addDeckNode(testDeckNode1);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode1, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(1, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
        testDeck1.deleteDeckNode(testDeckNode1);
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
        assertEquals("deck1", testDeck1.getDeckName());
    }

    @Test
    public void testDeleteHeadOfDeck() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
        testDeck1.deleteDeckNode(testDeckNode1);
        assertEquals(testDeckNode2, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode2, testDeck1.getCurrentlyViewing());
        assertEquals(2, testDeck1.getCardCounter());
    }

    @Test
    public void testDeleteTailOfDeck() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
        testDeck1.deleteDeckNode(testDeckNode3);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode2, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(2, testDeck1.getCardCounter());
    }

    @Test
    public void testDeleteAllCardsFromThreeCardDeck() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
        testDeck1.deleteDeckNode(testDeckNode1);
        assertEquals(testDeckNode2, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode2, testDeck1.getCurrentlyViewing());
        assertEquals(2, testDeck1.getCardCounter());
        testDeck1.deleteDeckNode(testDeckNode2);
        assertEquals(testDeckNode3, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode3, testDeck1.getCurrentlyViewing());
        assertEquals(1, testDeck1.getCardCounter());
        testDeck1.deleteDeckNode(testDeckNode3);
        assertEquals(null, testDeck1.getHead());
        assertEquals(null, testDeck1.getTail());
        assertEquals(null, testDeck1.getCurrentlyViewing());
        assertEquals(0, testDeck1.getCardCounter());
    }

    @Test
    public void testCardRelationshipsInThreeCardDeck() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode2, testDeck1.getHead().getNextDeckNode());
        assertEquals(testDeckNode3, testDeck1.getHead().getPrevDeckNode());
        assertEquals(testDeckNode1, testDeck1.getTail().getNextDeckNode());
        assertEquals(testDeckNode2, testDeck1.getTail().getPrevDeckNode());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
    }

    @Test
    public void testEditSingleCard() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.editDeckNode(testDeckNode1, "5+5", "10");
        assertEquals("5+5", testDeckNode1.getCard().getQuestion());
        assertEquals("10", testDeckNode1.getCard().getAnswer());
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode1, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(1, testDeck1.getCardCounter());
    }

    @Test
    public void testEditTwoCards() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.editDeckNode(testDeckNode1, "5+5", "10");
        testDeck1.editDeckNode(testDeckNode2, "20+20", "40");
        assertEquals("5+5", testDeckNode1.getCard().getQuestion());
        assertEquals("10", testDeckNode1.getCard().getAnswer());
        assertEquals("20+20", testDeckNode2.getCard().getQuestion());
        assertEquals("40", testDeckNode2.getCard().getAnswer());
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode2, testDeck1.getTail());
        assertEquals("5+5", testDeck1.getHead().getCard().getQuestion());
        assertEquals("10", testDeck1.getHead().getCard().getAnswer());
        assertEquals("20+20", testDeck1.getTail().getCard().getQuestion());
        assertEquals("40", testDeck1.getTail().getCard().getAnswer());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(2, testDeck1.getCardCounter());
    }

    @Test
    public void testSetCurrentlyViewingToDifferentCard() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        testDeckNode1.getCard().flipCard();
        assertFalse(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        testDeck1.setCurrentlyViewing(testDeckNode2);
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode2, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
        assertTrue(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
    }

    @Test
    public void testFlipNoCardsBackToQuestionSide() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        assertTrue(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        testDeck1.flipAllCardsBackToQuestionSide();
        assertTrue(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
    }

    @Test
    public void testFlipOneCardBackToQuestionSide() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        testDeckNode1.getCard().flipCard();
        assertFalse(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        testDeck1.flipAllCardsBackToQuestionSide();
        assertTrue(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
    }

    @Test
    public void testFlipTwoCardsBackToQuestionSide() {
        testDeck1.addDeckNode(testDeckNode1);
        testDeck1.addDeckNode(testDeckNode2);
        testDeck1.addDeckNode(testDeckNode3);
        testDeckNode1.getCard().flipCard();
        testDeckNode2.getCard().flipCard();
        assertFalse(testDeckNode1.getCard().getSide());
        assertFalse(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        testDeck1.flipAllCardsBackToQuestionSide();
        assertTrue(testDeckNode1.getCard().getSide());
        assertTrue(testDeckNode2.getCard().getSide());
        assertTrue(testDeckNode3.getCard().getSide());
        assertEquals(testDeckNode1, testDeck1.getHead());
        assertEquals(testDeckNode3, testDeck1.getTail());
        assertEquals(testDeckNode1, testDeck1.getCurrentlyViewing());
        assertEquals(3, testDeck1.getCardCounter());
    }


}
