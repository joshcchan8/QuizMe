package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a card with both a question and answer side.
public class Card implements Writable {
    private String question;        // question on the card
    private String answer;          // answer revealed when flipped
    private boolean onQuestionSide; // side the card is on

    // REQUIRES: question and answer have non-zero length
    // EFFECTS: creates a flashcard with a question on one side and
    // an answer on the other.
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.onQuestionSide = true;
    }

    public Card(String question, String answer, boolean onQuestionSide) {
        this.question = question;
        this.answer = answer;
        this.onQuestionSide = onQuestionSide;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public boolean getSide() {
        return this.onQuestionSide;
    }

    // MODIFIES: this
    // EFFECTS: sets the question on the card
    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    // MODIFIES: this
    // EFFECTS: sets the answer on the card
    public void setAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    // MODIFIES: this
    // EFFECTS: flips card to question side automatically
    public void flipOntoQuestionSide() {
        this.onQuestionSide = true;
    }

    // MODIFIES: this
    // EFFECTS: changes the displayed side on a card from the question
    // side to the answer side or the answer side to the question side,
    // depending on the current state of onQuestionSide.
    public void flipCard() {
        this.onQuestionSide = !this.onQuestionSide;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        json.put("onQuestionSide", onQuestionSide);
        return json;
    }
}
