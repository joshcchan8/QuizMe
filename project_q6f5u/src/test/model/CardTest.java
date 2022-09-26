package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Card
public class CardTest {

    Card testCard;

    @BeforeEach
    public void setup() {
        testCard = new Card("1+1", "2");
    }

    @Test
    public void testSetQuestion() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.setQuestion("2+2");
        assertEquals("2+2", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
    }

    @Test
    public void testSetAnswer() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.setAnswer("4");
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("4", testCard.getAnswer());
        assertTrue(testCard.getSide());
    }

    @Test
    public void testSetQuestionAndAnswerTwice() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.setQuestion("2+2");
        testCard.setAnswer("4");
        assertEquals("2+2", testCard.getQuestion());
        assertEquals("4", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.setQuestion("3+3");
        testCard.setAnswer("6");
        assertEquals("3+3", testCard.getQuestion());
        assertEquals("6", testCard.getAnswer());
        assertTrue(testCard.getSide());
    }

    @Test
    public void testFlipCardQuestionToAnswer() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.flipCard();
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertFalse(testCard.getSide());
    }

    @Test
    public void testFlipCardAnswerToQuestion() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.flipCard();
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertFalse(testCard.getSide());
        testCard.flipCard();
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
    }

    @Test
    public void testFlipToQuestionFromAnswerSide() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.flipCard();
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertFalse(testCard.getSide());
        testCard.flipOntoQuestionSide();
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
    }

    @Test
    public void testFlipToQuestionFromQuestionSide() {
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
        testCard.flipOntoQuestionSide();
        assertEquals("1+1", testCard.getQuestion());
        assertEquals("2", testCard.getAnswer());
        assertTrue(testCard.getSide());
    }
}