package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Decks decks = new Decks();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDecks() {
        try {
            Decks decks = new Decks();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDecks.json");
            writer.open();
            writer.write(decks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDecks.json");
            decks = reader.read();
            assertEquals(0, decks.getDecks().size());
            assertEquals(0, decks.getDeckNames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Decks decks = new Decks();
            decks.addDeckName("deck2");
            Card card1 = new Card("Question: 2+2", "Answer: 4");
            Card card2 = new Card("Question: 3+3", "Answer: 6");
            DeckNode deckNode1 = new DeckNode(card1);
            DeckNode deckNode2 = new DeckNode(card2);
            ArrayList<DeckNode> deckNodes = new ArrayList<DeckNode>();
            deckNodes.add(deckNode1);
            deckNodes.add(deckNode2);
            Deck deck = new Deck("deck2");
            deck.addDeckNode(deckNode1);
            deck.addDeckNode(deckNode2);
            deck.setDeckNodes(deckNodes);
            decks.addDeck("deck2", deck);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDecks.json");
            writer.open();
            writer.write(decks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDecks.json");
            Decks d = reader.read();
            assertEquals(1, d.getDeckNames().size());
            assertEquals(1, d.getDecks().size());
            assertEquals("deck2\n", d.listDeckNames());
            Deck deck2 = d.getDeck("deck2");
            ArrayList<DeckNode> deck2Nodes = deck2.getDeckNodes();
            DeckNode deck2Node1 = deck2Nodes.get(0);
            DeckNode deck2Node2 = deck2Nodes.get(1);
            Card deck2Card1 = deck2Node1.getCard();
            Card deck2Card2 = deck2Node2.getCard();
            checkCard(true, "Question: 2+2", "Answer: 4", deck2Card1);
            checkCard(true, "Question: 3+3", "Answer: 6", deck2Card2);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
