package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for DeckNode
public class DeckNodeTest {

    Card testCard1;
    Card testCard2;
    Card testCard3;
    Card testCard4;
    DeckNode testDeckNode1;
    DeckNode testDeckNode2;
    DeckNode testDeckNode3;
    DeckNode testDeckNode4;

    @BeforeEach
    public void setup() {
        testCard1 = new Card("Question: 1+1", "Answer: 2");
        testCard2 = new Card("Question: 2+2", "Answer: 4");
        testCard3 = new Card("Question: 3+3", "Answer: 6");
        testCard4 = new Card("Question: 4+4", "Answer: 8");
        testDeckNode1 = new DeckNode(testCard1);
        testDeckNode2 = new DeckNode(testCard2);
        testDeckNode3 = new DeckNode(testCard3);
        testDeckNode4 = new DeckNode(testCard4);
    }

    @Test
    public void testSetCardToNewCard() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setCard(testCard2);
        assertEquals(testCard2, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testSetCardToSameCard() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setCard(testCard1);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testSetCardTwice() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setCard(testCard2);
        assertEquals(testCard2, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setCard(testCard3);
        assertEquals(testCard3, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testSetNextDeckNodeFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setNextDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode2, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getNextDeckNode().getCard());
    }

    @Test
    public void testSetNextDeckNodeNotFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setNextDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode2, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getNextDeckNode().getCard());
        testDeckNode1.setNextDeckNode(testDeckNode3);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode3, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard3, testDeckNode1.getNextDeckNode().getCard());
    }

    @Test
    public void testSetPrevDeckNodeFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setPrevDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode2, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getPrevDeckNode().getCard());
    }

    @Test
    public void testSetPrevDeckNodeNotFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setPrevDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode2, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getPrevDeckNode().getCard());
        testDeckNode1.setPrevDeckNode(testDeckNode3);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode3, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard3, testDeckNode1.getPrevDeckNode().getCard());
    }

    @Test
    public void testSetNextAndPrevSameDeckNode() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setNextDeckNode(testDeckNode2);
        testDeckNode1.setPrevDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode2, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode2, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getNextDeckNode().getCard());
        assertEquals(testCard2, testDeckNode1.getPrevDeckNode().getCard());
        testDeckNode1.setNextDeckNode(testDeckNode3);
        testDeckNode1.setPrevDeckNode(testDeckNode3);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode3, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode3, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard3, testDeckNode1.getNextDeckNode().getCard());
        assertEquals(testCard3, testDeckNode1.getPrevDeckNode().getCard());
    }

    @Test
    public void testSetNextAndPrevDifferentDeckNode() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setNextDeckNode(testDeckNode2);
        testDeckNode1.setPrevDeckNode(testDeckNode3);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode2, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode3, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getNextDeckNode().getCard());
        assertEquals(testCard3, testDeckNode1.getPrevDeckNode().getCard());
        testDeckNode1.setNextDeckNode(testDeckNode3);
        testDeckNode1.setPrevDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode3, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode2, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard3, testDeckNode1.getNextDeckNode().getCard());
        assertEquals(testCard2, testDeckNode1.getPrevDeckNode().getCard());
    }

    @Test
    public void testRemoveNextDeckNodeFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.removeNextDeckNode();
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testRemoveNextDeckNodeNotFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setNextDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode2, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getNextDeckNode().getCard());
        testDeckNode1.removeNextDeckNode();
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testRemovePrevDeckNodeFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.removePrevDeckNode();
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testRemovePrevDeckNodeNotFromNull() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setPrevDeckNode(testDeckNode2);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode2, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getPrevDeckNode().getCard());
        testDeckNode1.removePrevDeckNode();
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }

    @Test
    public void testRemoveNextAndPrevDeckNode() {
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
        testDeckNode1.setNextDeckNode(testDeckNode2);
        testDeckNode1.setPrevDeckNode(testDeckNode3);
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(testDeckNode2, testDeckNode1.getNextDeckNode());
        assertEquals(testDeckNode3, testDeckNode1.getPrevDeckNode());
        assertEquals(testCard2, testDeckNode1.getNextDeckNode().getCard());
        assertEquals(testCard3, testDeckNode1.getPrevDeckNode().getCard());
        testDeckNode1.removeNextDeckNode();
        testDeckNode1.removePrevDeckNode();
        assertEquals(testCard1, testDeckNode1.getCard());
        assertEquals(null, testDeckNode1.getNextDeckNode());
        assertEquals(null, testDeckNode1.getPrevDeckNode());
    }


}
