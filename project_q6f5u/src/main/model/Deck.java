package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a single deck of flashcards in the form of a linked list
public class Deck implements Writable {
    private DeckNode head;
    private DeckNode tail;
    private DeckNode currentlyViewing;
    private int cardCounter;
    private String deckName;
    private ArrayList<DeckNode> deckNodes;

    // EFFECTS: constructs an empty deck
    public Deck(String deckName) {
        this.head = null;
        this.tail = null;
        this.currentlyViewing = null;
        this.cardCounter = 0;
        this.deckName = deckName;
        this.deckNodes = new ArrayList<DeckNode>();
    }

    // EFFECTS: constructs an empty deck
    public Deck(String deckName, int cardCounter) {
        this.head = null;
        this.tail = null;
        this.currentlyViewing = null;
        this.cardCounter = cardCounter;
        this.deckName = deckName;
        this.deckNodes = new ArrayList<DeckNode>();
    }

    public int getCardCounter() {
        return this.cardCounter;
    }

    public DeckNode getHead() {
        return this.head;
    }

    public DeckNode getTail() {
        return this.tail;
    }

    public DeckNode getCurrentlyViewing() {
        return this.currentlyViewing;
    }

    public String getDeckName() {
        return this.deckName;
    }

    public ArrayList<DeckNode> getDeckNodes() {
        return this.deckNodes;
    }

    // REQUIRES: added deckNode is not already in the linked list
    // MODIFIES: this
    // EFFECTS: adds the given deckNode to the end of the deck (it becomes the tail),
    // and changes the deck view to the deckNode that was added
    public void addDeckNode(DeckNode deckNode) {
        DeckNode newNode = deckNode;

        if (this.head == null) {
            this.head = newNode;
        } else {
            this.tail.setNextDeckNode(newNode);
        }

        newNode.setPrevDeckNode(this.tail);
        this.tail = newNode;
        this.tail.setNextDeckNode(this.head);
        this.head.setPrevDeckNode(this.tail);

        this.cardCounter += 1;
        this.currentlyViewing = this.head;
        this.deckNodes.add(newNode);

        EventLog.getInstance().logEvent(
                new Event("Added card with question " + deckNode.getCard().getQuestion()
                        + " and answer " + deckNode.getCard().getAnswer() + "."));
    }

    // REQUIRES: if deck is non-empty, provided deckNode must be in the deck
    // MODIFIES: this
    // EFFECTS: removes the given deckNode from the deck
    public void deleteDeckNode(DeckNode deckNodeToDelete) {
        if (this.head != null) {
            DeckNode nextDeckNode = this.head.getNextDeckNode();
            if (nextDeckNode == this.head) {
                this.head = this.tail = null;
                deckNodes.remove(nextDeckNode);
            }
            findAndDelete(nextDeckNode, deckNodeToDelete);
            this.cardCounter -= 1;
        }
        this.currentlyViewing = this.head;

        EventLog.getInstance().logEvent(
                new Event("Deleted card with question " + deckNodeToDelete.getCard().getQuestion()
                        + " and answer " + deckNodeToDelete.getCard().getAnswer() + "."));;
    }

    // MODIFIES: this
    // EFFECTS: takes the current deckNode and loops through the deck until it finds the deckNodeToDelete
    public void findAndDelete(DeckNode nextDeckNode, DeckNode deckNodeToDelete) {
        while (true) {
            if (nextDeckNode.equals(deckNodeToDelete)) {
                if (nextDeckNode.equals(this.head)) {
                    this.head = nextDeckNode.getNextDeckNode();
                } else if (nextDeckNode.equals(this.tail)) {
                    this.tail = nextDeckNode.getPrevDeckNode();
                }
                nextDeckNode.getPrevDeckNode().setNextDeckNode(nextDeckNode.getNextDeckNode());
                nextDeckNode.getNextDeckNode().setPrevDeckNode(nextDeckNode.getPrevDeckNode());
                nextDeckNode.removeNextDeckNode();
                nextDeckNode.removePrevDeckNode();
                deckNodes.remove(nextDeckNode);
                break;
            }
            nextDeckNode = nextDeckNode.getNextDeckNode();
        }
    }

    // REQUIRES: given deckNode is in the deck
    // MODIFIES: this
    // EFFECTS: changes the question and answer of a specific card in the deck
    public void editDeckNode(DeckNode deckNode, String newQuestion, String newAnswer) {

        EventLog.getInstance().logEvent(new Event("Edited card from question "
                + deckNode.getCard().getQuestion() + " and answer " + deckNode.getCard().getAnswer()
                + " to question " + newQuestion + " and answer " + newAnswer + "."));

        deckNode.getCard().setQuestion(newQuestion);
        deckNode.getCard().setAnswer(newAnswer);
    }

    // MODIFIES: this
    // EFFECTS: sets the given deckNode as the head of the deck
    public void setHead(DeckNode head) {
        this.head = head;
    }

    // MODIFIES: this
    // EFFECTS: sets the given deckNode as the tail of the deck
    public void setTail(DeckNode tail) {
        this.tail = tail;
    }

    // REQUIRES: deckNode card is not already in view
    // MODIFIES: this
    // EFFECTS: sets the given deckNode as currently being viewed. Also,
    // automatically flips cards back to question side when not being viewed.
    public void setCurrentlyViewing(DeckNode deckNode) {
        this.currentlyViewing.getCard().flipOntoQuestionSide();
        this.currentlyViewing = deckNode;
    }

    // MODIFIES: this
    // EFFECTS: flips all the cards in the deck back onto their question side.
    public void flipAllCardsBackToQuestionSide() {
        DeckNode cur = this.head;

        while (cur.getNextDeckNode() != this.head) {
            cur.getNextDeckNode().getCard().flipOntoQuestionSide();
            cur = cur.getNextDeckNode();
        }
        this.head.getCard().flipOntoQuestionSide();
    }

    // MODIFIES: this
    // EFFECTS: sets deckNodes to given list of deckNodes
    public void setDeckNodes(ArrayList<DeckNode> deckNodes) {
        this.deckNodes = deckNodes;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("head", 0);
        if (deckNodes.size() != 0) {
            json.put("tail", deckNodes.size() - 1);
        } else {
            json.put("tail", 0);
        }
        json.put("currentlyViewing", 0);
        json.put("cardCounter", cardCounter);
        json.put("deckName", deckName);
        json.put("deckNodes", deckNodesToJson());
        return json;
    }

    // EFFECTS: returns an arraylist of deckNodes in the deck
    private JSONArray deckNodesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (DeckNode deckNode : deckNodes) {
            jsonArray.put(deckNode.toJson());
        }

        return jsonArray;
    }
}
