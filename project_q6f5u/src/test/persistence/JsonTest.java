package persistence;

import model.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    void checkCard(boolean onQuestionSide, String question, String answer, Card card) {
        assertEquals(onQuestionSide, card.getSide());
        assertEquals(question, card.getQuestion());
        assertEquals(answer, card.getAnswer());
    }

}
