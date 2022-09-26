package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");
        try {
            Decks d = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDecks() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDecks.json");
        try {
            Decks d = reader.read();
            assertEquals(0, d.getDeckNames().size());
            assertEquals(0, d.getDecks().size());
        } catch (IOException e) {
            fail("Couldn't read from file.");
        }
    }

    @Test
    void testReaderGeneralDecks() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDecks.json");
        try {
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
            fail("Couldn't read from file.");
        }
    }
}
