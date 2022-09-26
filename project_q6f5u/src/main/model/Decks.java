package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Represents all the decks in the flashcard application
public class Decks implements Writable {
    private Map<String, Deck> decks;
    private ArrayList<String> deckNames;

    // EFFECTS: constructs an empty hashmap of decks and list of deck names
    public Decks() {
        this.decks = new HashMap<String, Deck>();
        this.deckNames = new ArrayList<String>();
    }

    public Map<String, Deck> getDecks() {
        return this.decks;
    }

    // EFFECTS: returns deck with the given name
    public Deck getDeck(String deckName) {
        return this.decks.get(deckName);
    }

    // EFFECTS: returns a list of all the deck names in the deck
    public ArrayList<String> getDeckNames() {
        return this.deckNames;
    }

    // MODIFIES: this
    // EFFECTS: adds an empty deck to the decks hashmap and the name to the array list
    public void createDeck(String deckName) {
        this.decks.put(deckName, new Deck(deckName));
        addDeckName(deckName);
    }

    // MODIFIES: this
    // EFFECTS: adds the given deck to the hashmap of decks
    public void addDeck(String deckName, Deck deck) {
        this.decks.put(deckName, deck);
        EventLog.getInstance().logEvent(new Event("New deck, " + deckName + ", has been created."));
    }

    // MODIFIES: this
    // EFFECTS: adds the given deckName to the array list of deckNames
    public void addDeckName(String deckName) {
        this.deckNames.add(deckName);
    }

    // MODIFIES: this
    // EFFECTS: removes the deck with the given name from the decks hashmap and from the deckNames list
    public void removeDeck(String deckName) {
        this.decks.remove(deckName);
        this.deckNames.remove(deckName);
        EventLog.getInstance().logEvent(new Event(deckName + " has been deleted."));
    }

    // EFFECTS: prints the names of all the decks in the decks hashmap
    public String listDeckNames() {
        String deckNames = "";
        for (String deckName : this.deckNames) {
            deckNames += deckName + "\n";
        }
        return deckNames;
    }

    // EFFECTS: returns true if decks contains the deck with the given deck name
    public boolean checkDecksContains(String deckName) {
        for (String n : this.deckNames) {
            if (deckName.equals(n.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("decks", decksToJson());
        json.put("deckNames", deckNamesToJson());
        return json;
    }

    // EFFECTS: returns decks as a JSON object
    private JSONArray decksToJson() {
        JSONArray decksArray = new JSONArray();
        for (Map.Entry<String, Deck> set : decks.entrySet()) {
            decksArray.put(deckToJson(set.getValue().toJson()));
        }
        return decksArray;
    }

    // EFFECTS: returns decks as a JSON object
    private JSONObject deckToJson(JSONObject deck) {
        JSONObject json = new JSONObject();
        json.put("deck", deck);
        return json;
    }


    // EFFECTS: returns deckNames in the decks as a JSON array
    private JSONArray deckNamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String deckName : deckNames) {
            jsonArray.put(deckNameToJson(deckName));
        }
        return jsonArray;
    }

    // EFFECTS: returns deckName in the decks as a JSON object
    private JSONObject deckNameToJson(String deckName) {
        JSONObject json = new JSONObject();
        json.put("deckName", deckName);
        return json;
    }
}

