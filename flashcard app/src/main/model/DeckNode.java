package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents one node in the deck (linked list), containing the information of a single card in the deck.
public class DeckNode implements Writable {
    private Card card;
    private DeckNode next;
    private DeckNode prev;

    public DeckNode(Card card) {
        this.card = card;        // the card held in the deckNode
        this.next = null;        // the next deckNode in the deck
        this.prev = null;        // the previous deckNode in the deck
    }

    public DeckNode() {
        this.card = null;        // the card held in the deckNode
        this.next = null;        // the next deckNode in the deck
        this.prev = null;        // the previous deckNode in the deck
    }

    public Card getCard() {
        return this.card;
    }

    public DeckNode getNextDeckNode() {
        return this.next;
    }

    public DeckNode getPrevDeckNode() {
        return this.prev;
    }

    // MODIFIES: this
    // EFFECTS: sets the value of the DeckNode to the given Card
    public void setCard(Card newCard) {
        this.card = newCard;
    }

    // MODIFIES: this
    // EFFECTS: causes the next pointer to pointer towards the given DeckNode
    public void setNextDeckNode(DeckNode deckNode) {
        this.next = deckNode;
    }

    // MODIFIES: this
    // EFFECTS: causes the previous pointer to pointer towards the given DeckNode
    public void setPrevDeckNode(DeckNode deckNode) {
        this.prev = deckNode;
    }

    // MODIFIES: this
    // EFFECTS: makes the next DeckNode pointer point to nothing
    public void removeNextDeckNode() {
        this.next = null;
    }

    // MODIFIES: this
    // EFFECTS: makes the previous DeckNode pointer point to nothing
    public void removePrevDeckNode() {
        this.prev = null;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("card", card.toJson());
        return json;
    }
}
