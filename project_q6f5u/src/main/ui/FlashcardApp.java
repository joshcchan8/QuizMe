package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the Flashcard Application
public class FlashcardApp {
    private static final String JSON_STORE = "./data/decks.json";
    private Decks decks;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the flashcard application
    public FlashcardApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        decks = new Decks();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFlashcards();
    }

    // MODIFIES: this
    // EFFECTS: initializes decks and launches menu
    private void runFlashcards() {
        init();
        runMainMenu();
    }

    // EFFECTS: process user input in the main menu
    private void runMainMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        System.out.print("\nGoodbye!");
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processMainCommand(String command) {
        if (command.equals("c")) {
            createDeck();
        } else if (command.equals("r")) {
            removeExistingDeck();
        } else if (this.decks.checkDecksContains(command)) {
            viewDeck(this.decks.getDeck(command), command);
        } else if (command.equals("s")) {
            saveDecks();
        } else if (command.equals("l")) {
            loadDecks();
        } else {
            System.out.println("Please enter a valid selection.");
        }
    }

    // MODIFIES: this
    // EFFECTS initializes Decks
    private void init() {
        decks = new Decks();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMainMenu() {
        System.out.println("\nHere are all of the decks you've created:");
        if (this.decks.getDeckNames().size() == 0) {
            System.out.println("No decks yet.");
        }
        System.out.println(this.decks.listDeckNames());;
        System.out.println("Type the name a deck you want to view or select one of the following:");
        System.out.println("\tc -> create a new deck");
        System.out.println("\tr -> remove an existing deck");
        System.out.println("\ts -> save decks to file");
        System.out.println("\tl -> load decks from file");
        System.out.println("\tq -> quit\n");
    }

    // MODIFIES: this
    // EFFECTS: adds a new deck to decks and views the deck
    private void createDeck() {
        System.out.println("Please enter the name of your new deck (lower-case only): ");
        String name = input.next();
        this.decks.createDeck(name);
        viewDeck(this.decks.getDeck(name), name);
    }

    // MODIFIES: this
    // EFFECTS: deletes an existing deck in the decks hashmap
    private void removeExistingDeck() {
        System.out.println("Please enter the name of the deck you want to remove: ");
        String name = input.next();
        if (this.decks.checkDecksContains(name.toLowerCase())) {
            this.decks.removeDeck(name);
            System.out.println("Deck removed!");
        } else {
            System.out.println("There is no deck with that name!");
        }
        runMainMenu();
    }

    // EFFECTS: view the deck menu with options regarding the given deck
    private void viewDeck(Deck deck, String deckName) {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayDeckMenu(deckName);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processDeckCommand(command, deck);
            }
        }

        System.out.print("\nGoodbye!");
        System.exit(0);
    }

    // EFFECTS: display deck menu options to user
    private void displayDeckMenu(String deckName) {
        System.out.println("\nDeck currently in view: " + deckName.toLowerCase()
                + ". Select one of the following options:\n");
        System.out.println("\tu -> use, edit, or delete cards from this deck");
        System.out.println("\ta -> add new card to this deck");
        System.out.println("\tb -> go back to main menu");
        System.out.println("\tq -> quit\n");
    }

    // EFFECTS: processes user command in deck menu
    private void processDeckCommand(String command, Deck deck) {
        if (command.equals("u")) {
            useDeck(deck);
        } else if (command.equals("a")) {
            addCardsToDeck(deck);
        } else if (command.equals("b")) {
            runMainMenu();
        } else {
            System.out.println("Please enter a valid selection.");
        }
    }

    // EFFECTS: allows user to flip through deck and use the flashcards in the deck
    private void useDeck(Deck deck) {
        if (deck.getHead() == deck.getTail() && deck.getHead() == null) {
            System.out.println("This deck is empty! \n");
            viewDeck(deck, deck.getDeckName());
        }

        viewingInstructions();
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            if (deck.getCurrentlyViewing().getCard().getSide() == true) {
                System.out.println(deck.getCurrentlyViewing().getCard().getQuestion());
            } else {
                System.out.println(deck.getCurrentlyViewing().getCard().getAnswer());
            }

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCardCommand(command, deck.getCurrentlyViewing(), deck);
            }
        }

        System.out.print("\nGoodbye!");
        System.exit(0);
    }

    // EFFECTS: provides instructions for viewing a deck
    private void viewingInstructions() {
        System.out.println("\nSelect one of the following:");
        System.out.println("\tn -> view next card");
        System.out.println("\tp -> view previous card");
        System.out.println("\tf -> flip card");
        System.out.println("\te -> edit card");
        System.out.println("\td -> delete card");
        System.out.println("\tb -> go back to deck menu");
        System.out.println("\tq -> quit");
        System.out.println("\n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to add cards to the deck
    private void addCardsToDeck(Deck deck) {
        System.out.println("Enter question: ");
        String newQuestion = "Question: " + input.next();
        System.out.println("Enter answer: ");
        String newAnswer = "Answer: " + input.next();

        deck.addDeckNode(new DeckNode(new Card(newQuestion, newAnswer)));
        System.out.println("Card successfully added!");
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit, delete, or flip cards.
    private void processCardCommand(String command, DeckNode deckNode, Deck deck) {
        if (command.equals("n")) {
            deck.setCurrentlyViewing(deckNode.getNextDeckNode());
            System.out.println("\n");
        } else if (command.equals("p")) {
            deck.setCurrentlyViewing(deckNode.getPrevDeckNode());
            System.out.println("\n");
        } else if (command.equals("f")) {
            flipDeckNodeCard(deckNode);
        } else if (command.equals("e")) {
            editCardInDeck(deck, deckNode);
        } else if (command.equals("d")) {
            deleteCardFromDeck(deck, deckNode);
        } else if (command.equals("b")) {
            deck.flipAllCardsBackToQuestionSide();
            viewDeck(deck, deck.getDeckName());
        } else {
            System.out.println("Please enter a valid selection.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to delete cards from the deck
    private void deleteCardFromDeck(Deck deck, DeckNode deckNode) {
        deck.deleteDeckNode(deckNode);
        System.out.println("Card deleted!");

        if (deck.getCurrentlyViewing() == null) {
            System.out.println("Deck is now empty! Returning to menu page.");
            viewDeck(deck, deck.getDeckName());
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit cards in the deck
    private void editCardInDeck(Deck deck, DeckNode deckNode) {

        System.out.println("Enter new question: ");
        String newQuestion = input.next();
        System.out.println("Enter new answer: ");
        String newAnswer = input.next();

        deck.editDeckNode(deckNode, newQuestion, newAnswer);

        System.out.println("Card successfully updated!\n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to flip the card in the deckNode currently being viewed
    private void flipDeckNodeCard(DeckNode deckNode) {
        deckNode.getCard().flipCard();
    }

    // EFFECTS: saves the workroom to file
    private void saveDecks() {
        try {
            jsonWriter.open();
            jsonWriter.write(decks);
            jsonWriter.close();
            System.out.println("Saved your decks to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads decks from file
    private void loadDecks() {
        try {
            decks = jsonReader.read();
            System.out.println("Loaded your decks from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

