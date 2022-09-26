package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads deck from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Decks read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDecks(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses decks from JSON object and returns it
    private Decks parseDecks(JSONObject jsonObject) {
        Decks decks = new Decks();
        addDeckNames(decks, jsonObject);
        addDecks(decks, jsonObject);
        return decks;
    }

    // MODIFIES: decks
    // EFFECTS: parses deckNames from JSON object and adds them to decks
    private void addDeckNames(Decks decks, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("deckNames");
        for (Object json : jsonArray) {
            JSONObject nextDeckName = (JSONObject) json;
            addDeckName(decks, nextDeckName);
        }
    }

    // MODIFIES: decks
    // EFFECTS: parses deckName from JSON object and adds it to decks
    private void addDeckName(Decks decks, JSONObject jsonObject) {
        String name = jsonObject.getString("deckName");
        decks.addDeckName(name);
    }

    // MODIFIES: decks
    // EFFECTS: parses decks from JSON object and adds them to decks
    private void addDecks(Decks decks, JSONObject jsonObject) {
        JSONArray jsonArray =  jsonObject.getJSONArray("decks");
        for (Object json: jsonArray) {
            JSONObject nextDeck = (JSONObject) json;
            addDeck(decks, nextDeck);
        }
    }

    // MODIFIES: decks
    // EFFECTS: parses deck from JSON object and adds it to decks
    private void addDeck(Decks decks, JSONObject jsonObject) {
        JSONObject json = jsonObject.getJSONObject("deck");
        String deckName = json.getString("deckName");
        int cardCounter = json.getInt("cardCounter");
        Deck deck = new Deck(deckName, cardCounter);
        addDeckNode(json, deck);
        ArrayList<DeckNode> deckNodes = deck.getDeckNodes();
        addHead(json, deck, deckNodes);
        addTail(json, deck, deckNodes);
        addCurrentlyViewing(json, deck, deckNodes);
        decks.addDeck(deckName, deck);
    }

    // MODIFIES: decks
    // EFFECTS: parses deckNodes from JSON object and adds them to decks
    private void addHead(JSONObject jsonObject, Deck deck, ArrayList<DeckNode> deckNodes) {
        int headIndex = jsonObject.getInt("head");
        if (deckNodes.size() != 0) {
            deck.setHead(deckNodes.get(headIndex));
        }
    }

    // MODIFIES: decks
    // EFFECTS: parses deckNodes from JSON object and adds them to decks
    private void addTail(JSONObject jsonObject, Deck deck, ArrayList<DeckNode> deckNodes) {
        int tailIndex = jsonObject.getInt("tail");
        if (deckNodes.size() != 0) {
            deck.setTail(deckNodes.get(tailIndex));
        }
    }

    // MODIFIES: decks
    // EFFECTS: parses deckNodes from JSON object and adds them to decks
    private void addCurrentlyViewing(JSONObject jsonObject, Deck deck, ArrayList<DeckNode> deckNodes) {
        int currentlyViewingIndex = jsonObject.getInt("currentlyViewing");
        if (deckNodes.size() != 0) {
            deck.setCurrentlyViewing(deckNodes.get(currentlyViewingIndex));
        }
    }

    private void addDeckNode(JSONObject jsonObject, Deck deck) {
        JSONArray deckNodes = jsonObject.getJSONArray("deckNodes");
        ArrayList<DeckNode> listOfDeckNodes = new ArrayList<>();
        for (Object json : deckNodes) {
            DeckNode newDeckNode = new DeckNode();
            JSONObject deckNode = (JSONObject) json;
            addCard(newDeckNode, deckNode);
            listOfDeckNodes.add(newDeckNode);
            deck.addDeckNode(newDeckNode);
        }
        deck.setDeckNodes(listOfDeckNodes);
    }

    // MODIFIES: decks
    // EFFECTS: parses Cards from JSON object and adds them to decks
    private void addCard(DeckNode newDeckNode, JSONObject deckNode) {
        JSONObject cardObject = deckNode.getJSONObject("card");
        String question = cardObject.getString("question");
        String answer = cardObject.getString("answer");
        boolean onQuestionSide = cardObject.getBoolean("onQuestionSide");
        Card card = new Card(question, answer, onQuestionSide);
        newDeckNode.setCard(card);
    }


}
