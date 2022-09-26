package ui;

import model.*;
import model.Event;
import persistence.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

// Starts the GUI for the Flashcard App
public class MainMenu extends JFrame implements ActionListener {

    private static final String DECKS_FILE = "./data/decks.txt";
    private Decks decks;
    private Deck deckInView;
    private String deckNameInView;

    private JTextArea displayArea;

    private JPanel mainPanel;
    private JPanel mainMenuHeader;
    private JPanel mainMenuBody;
    private JPanel mainMenuFooter;
    private JLabel searchForDeck;
    private JTextField deckSearchBar;
    private String searchedName;
    private JButton searchButton;

    private JPanel creationPanel;
    private JLabel enterNewDeckName;
    private JTextField deckNameToCreate;
    private JButton createDeckButton;
    private String newDeckName;

    private JPanel removalPanel;
    private JLabel enterDeckNameToRemove;
    private JTextField deckNameToRemove;
    private JButton removeDeckButton;
    private String removeDeckName;

    private JPanel popUp;
    private JLabel popUpMessage;

    private JPanel deckPanel;
    private JPanel deckPanelHeader;
    private JPanel deckPanelBody;
    private JPanel deckPanelFooter;
    private JButton nextButton;
    private JButton prevButton;
    private JButton addCardButton;
    private JButton flipButton;
    private JButton editButton;
    private JButton deleteButton;

    private JButton mainMenuButton;
    private JButton createButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    private JPanel addCardPanel;
    private JLabel enterNewQuestion;
    private JLabel enterNewAnswer;
    private JTextField newQuestion;
    private JTextField newAnswer;
    private String question;
    private String answer;
    private JButton makeCardButton;

    private JPanel editCardPanel;
    private JLabel enterEditedQuestion;
    private JLabel enterEditedAnswer;
    private JTextField editedQuestion;
    private JTextField editedAnswer;
    private String questionString;
    private String answerString;
    private JButton editCardButton;

    // EFFECTS: creates a GUI MainMenu
    public MainMenu() {
        super("FlashCardApp");

        EventLog.getInstance().clear();

        decks = new Decks();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        resetSize();

        initializeMainMenuButton();
        initializeDecksMenu();

        mainPanel.setVisible(true);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu panel
    public void initializeDecksMenu() {
        Container c = getContentPane();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainMenuHeader = new JPanel();
        mainMenuBody = new JPanel();
        mainMenuFooter = new JPanel();

        mainMenuLayout();

        JLabel header = new JLabel("Here are all of the decks you've created:", SwingConstants.CENTER);
        styleMainMenuLabel(header);
        mainMenuHeader.add(header);

        organizeMainMenuBackground();

        setPanelBorders(mainMenuHeader, mainMenuBody, mainMenuFooter);

        initializeMainMenuButtons();

        listDecks(decks);
        searchForDecks();

        addPanelsToMainPanel(mainPanel, mainMenuHeader, mainMenuBody, mainMenuFooter);
        c.add(mainPanel);

        mainPanel.setVisible(true);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets the layout for the three components of the main menu.
    private void mainMenuLayout() {
        FlowLayout footerLayout = new FlowLayout(FlowLayout.CENTER, 20, 60);
        FlowLayout bodyLayout = new FlowLayout(FlowLayout.CENTER, 10, 20);
        FlowLayout headerLayout = new FlowLayout(FlowLayout.CENTER, 10, 40);

        mainMenuHeader.setLayout(headerLayout);
        mainMenuBody.setLayout(bodyLayout);
        mainMenuFooter.setLayout(footerLayout);
    }

    // EFFECTS: styles the main menu heading
    private void styleMainMenuLabel(JLabel header) {
        header.setFont(new Font("Helvetica", Font.BOLD, 35));
        header.setBounds(0, 0, 800, 200);
    }

    // EFFECTS: styles the deck menu heading
    private void styleDeckMenuLabel(JLabel header) {
        header.setFont(new Font("Helvetica", Font.BOLD, 35));
        header.setBounds(0, 0, 800, 175);
    }

    // MODIFIES: this
    // EFFECTS: lists all decks on the main menu page
    private void listDecks(Decks decks) {
        for (String deckName : decks.getDeckNames()) {
            JLabel deckLabel = new JLabel(deckName);
            styleLabel(deckLabel);
            deckLabel.setSize(new Dimension(60,30));
            mainMenuBody.add(deckLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs the search bar, label, and button
    private void searchForDecks() {
        searchForDeck = new JLabel("Enter the name of a deck you want to view:");
        deckSearchBar = new JTextField(20);
        styleLabel(searchForDeck);

        searchButton = new JButton("Search");
        styleButton(searchButton);

        searchButton.addActionListener(this);
        searchButton.setActionCommand("search");

        mainMenuHeader.add(searchForDeck);
        mainMenuHeader.add(deckSearchBar);
        mainMenuHeader.add(searchButton);
    }

    // MODIFIES: this
    // EFFECTS: finds the deck with the given name and opens a new panel for it
    private void search() {
        searchedName = deckSearchBar.getText();
        if (decks.getDeckNames().contains(searchedName)) {
            Deck deck = decks.getDeck(searchedName);
            makeNewDeckPanel(deck, searchedName);
        } else {
            String noDeck = "There is no deck with that name!";
            popUpMessage(noDeck, mainPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the layout of the main menu background
    private void organizeMainMenuBackground() {
        mainMenuHeader.setBackground(Color.GRAY);
        mainMenuHeader.setMaximumSize(new Dimension(800,200));
        mainMenuHeader.setMinimumSize(new Dimension(800,200));
        mainMenuBody.setBackground(Color.WHITE);
        mainMenuBody.setMaximumSize(new Dimension(800,350));
        mainMenuBody.setMinimumSize(new Dimension(800,350));
        mainMenuFooter.setBackground(Color.GRAY);
        mainMenuFooter.setMaximumSize(new Dimension(800,150));
        mainMenuFooter.setMinimumSize(new Dimension(800,150));
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons on the main menu
    private void initializeMainMenuButtons() {
        createButton = new JButton("Create new deck");
        removeButton = new JButton("Remove deck");
        saveButton = new JButton("Save decks to file");
        loadButton = new JButton("Load decks from file");
        quitButton = new JButton("Quit");

        addButton(createButton, mainMenuFooter);
        addButton(removeButton, mainMenuFooter);
        addButton(saveButton, mainMenuFooter);
        addButton(loadButton, mainMenuFooter);
        addButton(quitButton, mainMenuFooter);

        addActionToMainButtons();
    }

    // EFFECTS: adds the button the given panel
    private void addButton(JButton button, JPanel panel) {
        styleButton(button);
        panel.add(button);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds actions to the main menu buttons
    private void addActionToMainButtons() {
        createButton.addActionListener(this);
        createButton.setActionCommand("Create new deck");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("Remove existing deck");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save decks to file");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load decks from file");
        quitButton.addActionListener(this);
        quitButton.setActionCommand("Quit");
    }

    // EFFECTS: performs an action based on the button clicked
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Create new deck")) {
            initializeCreationPanel();
        } else if (ae.getActionCommand().equals("Create deck")) {
            createNewDeck();
        } else if (ae.getActionCommand().equals("Remove existing deck")) {
            initializeRemovalPanel();
        } else if (ae.getActionCommand().equals("Remove deck")) {
            removeDeck();
        } else if (ae.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (ae.getActionCommand().equals("Save decks to file")) {
            saveDecks();
        } else if (ae.getActionCommand().equals("Load decks from file")) {
            loadDecks();
        } else if (ae.getActionCommand().equals("Quit")) {
            popUpImage();
        } else {
            cardActionPerformed(ae);
        }
    }

    // EFFECTS: performs a card action based on the button clicked
    private void cardActionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("next card")) {
            nextCard();
        } else if (ae.getActionCommand().equals("previous card")) {
            previousCard();
        } else if (ae.getActionCommand().equals("flip card")) {
            flipCard();
        } else if (ae.getActionCommand().equals("add new card")) {
            addCardPanel();
        } else if (ae.getActionCommand().equals("add card")) {
            addCard();
        } else if (ae.getActionCommand().equals("edit existing card")) {
            editCardPanel();
        } else if (ae.getActionCommand().equals("edit card")) {
            editCard();
        } else if (ae.getActionCommand().equals("delete existing card")) {
            deleteCard();
        } else if (ae.getActionCommand().equals("search")) {
            search();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the return to main menu button
    private void initializeMainMenuButton() {
        mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the panel for creating a new deck
    public void initializeCreationPanel() {
        mainPanel.setVisible(false);
        resetSmallSize();

        creationPanel = new JPanel();
        creationPanel.setBackground(Color.GRAY);

        FlowLayout creationPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 110);
        creationPanel.setLayout(creationPanelLayout);
        enterNewDeckName = new JLabel("Enter the name of your new deck:");
        styleLabel(enterNewDeckName);
        deckNameToCreate = new JTextField(10);

        addMainMenuButton(mainMenuButton, creationPanel);
        creationPanel.add(enterNewDeckName);
        creationPanel.add(deckNameToCreate);

        addCreateDeckButton();

        add(creationPanel);
        creationPanel.setVisible(true);
    }

    // EFFECTS: adds styling to the given label
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Helvetica", Font.BOLD, 18));
    }

    // EFFECTS: adds styling to the given button
    private void styleButton(JButton button) {
        button.setFont(new Font("Helvetica", Font.BOLD, 12));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.BLACK);
    }

    // EFFECTS: adds a return to main menu button to the given panel
    private void addMainMenuButton(JButton button, JPanel panel) {
        styleButton(button);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: adds the create deck button to the creation panel
    private void addCreateDeckButton() {
        createDeckButton = new JButton("Create deck");
        styleButton(createDeckButton);
        createDeckButton.setActionCommand("Create deck");
        createDeckButton.addActionListener(this);
        creationPanel.add(createDeckButton);
    }

    // MODIFIES: this
    // EFFECTS: creates a new deck with specified newDeckName
    private void createNewDeck() {
        newDeckName = deckNameToCreate.getText();
        if (newDeckName.isEmpty()) {
            String emptyDeckName = "Please enter a deck name!";
            popUpMessage(emptyDeckName, creationPanel);
        } else {
            Deck deck = new Deck(newDeckName);
            decks.addDeck(newDeckName, deck);
            decks.addDeckName(newDeckName);
            deckInView = deck;
            makeNewDeckPanel(deck, newDeckName);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the panel for removing a deck
    public void initializeRemovalPanel() {
        mainPanel.setVisible(false);
        resetSmallSize();

        removalPanel = new JPanel();
        removalPanel.setBackground(Color.GRAY);

        FlowLayout removalPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 110);
        removalPanel.setLayout(removalPanelLayout);
        enterDeckNameToRemove = new JLabel("Enter the name of deck to remove:");
        styleLabel(enterDeckNameToRemove);
        deckNameToRemove = new JTextField(10);

        addMainMenuButton(mainMenuButton, removalPanel);
        removalPanel.add(enterDeckNameToRemove);
        removalPanel.add(deckNameToRemove);

        addRemoveDeckButton();

        add(removalPanel);
        removalPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds the remove deck button to the removal panel
    private void addRemoveDeckButton() {
        removeDeckButton = new JButton("Remove deck");
        styleButton(removeDeckButton);
        removeDeckButton.setActionCommand("Remove deck");
        removeDeckButton.addActionListener(this);
        removalPanel.add(removeDeckButton);
    }

    // MODIFIES: this
    // EFFECTS: removes an existing deck with the specified removeDeckName;
    private void removeDeck() {
        removeDeckName = deckNameToRemove.getText();
        if (!decks.getDeckNames().contains(removeDeckName)) {
            String noDeckName = "There is no deck with that name!";
            popUpMessage(noDeckName, mainPanel);
        } else {
            this.decks.removeDeck(removeDeckName);
            String deckNameRemoved = "Deck has been removed!";
            popUpMessage(deckNameRemoved, mainPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the panel for viewing and modifying a specific deck
    private void makeNewDeckPanel(Deck deck, String deckName) {
        closeAllButDeckPanel();

        resetSize();

        Container c = getContentPane();

        deckInView = deck;
        deckNameInView = deckName;

        deckPanel = new JPanel();
        deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));

        deckPanelHeader = new JPanel();
        deckPanelBody = new JPanel();
        deckPanelFooter = new JPanel();

        deckPanelLayout();

        JLabel header = new JLabel("Deck currently in view: " + deckNameInView,
                SwingConstants.CENTER);
        styleDeckMenuLabel(header);

        deckPanelHeader.add(header);

        cardDisplay(deckInView);

        organizeDeckMenuBackground();

        setPanelBorders(deckPanelHeader, deckPanelBody, deckPanelFooter);

        initializeDeckButtons();

        addPanelsToMainPanel(deckPanel, deckPanelHeader, deckPanelBody, deckPanelFooter);
        c.add(deckPanel);

        deckPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets the layout for the deck panel
    private void deckPanelLayout() {
        FlowLayout footerLayout = new FlowLayout(FlowLayout.CENTER, 20, 35);
        FlowLayout bodyLayout = new FlowLayout(FlowLayout.CENTER, 0, 50);

        deckPanelHeader.setLayout(null);
        deckPanelBody.setLayout(bodyLayout);
        deckPanelFooter.setLayout(footerLayout);
    }

    // MODIFIES: this
    // EFFECTS: organizes the background for the deck panel
    private void organizeDeckMenuBackground() {
        deckPanelHeader.setBackground(Color.GRAY);
        deckPanelHeader.setMaximumSize(new Dimension(800,175));
        deckPanelHeader.setMinimumSize(new Dimension(800,175));
        deckPanelBody.setBackground(Color.WHITE);
        deckPanelBody.setMaximumSize(new Dimension(800,350));
        deckPanelBody.setMinimumSize(new Dimension(800,350));
        deckPanelFooter.setBackground(Color.GRAY);
        deckPanelFooter.setMaximumSize(new Dimension(800,175));
        deckPanelFooter.setMinimumSize(new Dimension(800,175));
    }

    // MODIFIES: this
    // EFFECTS: adds a border to the given panels
    private void setPanelBorders(JPanel panel1, JPanel panel2, JPanel panel3) {
        Border br = BorderFactory.createLineBorder(Color.BLACK);
        panel1.setBorder(br);
        panel2.setBorder(br);
        panel3.setBorder(br);
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons to use when viewing and modifying a specific deck
    private void initializeDeckButtons() {
        nextButton = new JButton("next card");
        prevButton = new JButton("previous card");
        flipButton = new JButton("flip card");
        addCardButton = new JButton("add new card");
        editButton = new JButton("edit card");
        deleteButton = new JButton("delete card");

        addButton(nextButton, deckPanelFooter);
        addButton(prevButton, deckPanelFooter);
        addButton(flipButton, deckPanelFooter);
        addButton(addCardButton, deckPanelFooter);
        addButton(editButton, deckPanelFooter);
        addButton(deleteButton, deckPanelFooter);

        addMainMenuButton(mainMenuButton, deckPanelFooter);

        addActionToDeckButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds actions to the buttons on the deck panels
    private void addActionToDeckButtons() {
        nextButton.addActionListener(this);
        nextButton.setActionCommand("next card");
        prevButton.addActionListener(this);
        prevButton.setActionCommand("previous card");
        flipButton.addActionListener(this);
        flipButton.setActionCommand("flip card");
        addCardButton.addActionListener(this);
        addCardButton.setActionCommand("add new card");
        editButton.addActionListener(this);
        editButton.setActionCommand("edit existing card");
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("delete existing card");
    }

    // MODIFIES: this
    // EFFECTS: adds three panels to the main panel
    private void addPanelsToMainPanel(JPanel deckPanel, JPanel panel1, JPanel panel2, JPanel panel3) {
        deckPanel.add(panel1);
        deckPanel.add(panel2);
        deckPanel.add(panel3);
    }

    // MODIFIES: this
    // EFFECTS: sets the size of the frame back to the large regular size
    private void resetSize() {
        setMaximumSize(new Dimension(800, 700));
        setMinimumSize(new Dimension(800, 700));
        setSize(new Dimension(800,700));
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: sets the size of the frame back to the smaller size
    private void resetSmallSize() {
        setMinimumSize(new Dimension(800, 300));
        setMaximumSize(new Dimension(800, 300));
        setSize(new Dimension(800,300));
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: creates a popUp message to inform the user of an event
    public void popUpMessage(String message, JPanel previousPanel) {
        closeAllPanels();
        resetSmallSize();

        popUp = new JPanel();
        popUpMessage = new JLabel(message, SwingConstants.CENTER);
        styleLabel(popUpMessage);

        popLayout();

        add(popUp);
        popUp.setVisible(true);

        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                popUp.setVisible(false);
                if (previousPanel.equals(mainPanel)) {
                    returnToMainMenu();
                } else if (previousPanel.equals(deckPanel)) {
                    resetSize();
                } else {
                    previousPanel.setVisible(true);
                    resetSmallSize();
                }
            }
        };
        timer.schedule(task, 1500);
    }

    // MODIFIES: this
    // EFFECTS: sets the layout for the popUp message
    private void popLayout() {
        FlowLayout popLayout = new FlowLayout(FlowLayout.CENTER, 20, 110);
        popUp.setLayout(popLayout);
        popUp.setBackground(Color.GRAY);
        popUp.add(popUpMessage);
    }

    // MODIFIES: this
    // EFFECTS: closes all the panels in the gui except for the pop up
    private void closeAllPanels() {
        if (creationPanel != null) {
            creationPanel.setVisible(false);
        }
        if (removalPanel != null) {
            removalPanel.setVisible(false);
        }
        if (deckPanel != null) {
            deckPanel.setVisible(false);
        }
        if (mainPanel != null) {
            mainPanel.setVisible(false);
        }
        if (addCardPanel != null) {
            addCardPanel.setVisible(false);
        }
        if (editCardPanel != null) {
            editCardPanel.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: closes all the panels in the gui except for the deck panel
    private void closeAllButDeckPanel() {
        if (creationPanel != null) {
            creationPanel.setVisible(false);
        }
        if (removalPanel != null) {
            removalPanel.setVisible(false);
        }
        if (mainPanel != null) {
            mainPanel.setVisible(false);
        }
        if (addCardPanel != null) {
            addCardPanel.setVisible(false);
        }
        if (editCardPanel != null) {
            editCardPanel.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the decks created to file
    private void saveDecks() {
        try {
            JsonWriter writer = new JsonWriter(DECKS_FILE);
            writer.open();
            writer.write(decks);
            writer.close();
            popUpMessage("Decks have been saved to file " + DECKS_FILE, mainPanel);
            System.out.println("Decks have been saved to file " + DECKS_FILE);
        } catch (FileNotFoundException e) {
            popUpMessage(DECKS_FILE + " was not found, so decks could not be saved.", mainPanel);
            System.out.println(DECKS_FILE + " was not found, so decks could not be saved.");
        } catch (NullPointerException e) {
            popUpMessage("Please load the file before trying to save it.", mainPanel);
            System.out.println("Please load the file before trying to save it.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the decks from file
    private void loadDecks() {
        try {
            JsonReader jsonReader = new JsonReader(DECKS_FILE);
            decks = jsonReader.read();
            popUpMessage("Decks have been loaded from " + DECKS_FILE, mainPanel);
            System.out.println("Decks have been loaded from " + DECKS_FILE);
        } catch (IOException e) {
            popUpMessage("No decks have been saved!", mainPanel);
            System.out.println("No decks have been saved!");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays a card's question and answer side, depending on the buttons pressed
    private void cardDisplay(Deck deckInView) {
        Deck deck = deckInView;

        if (deck.getDeckNodes().isEmpty()) {
            displayArea = new JTextArea("This deck is empty!", 4, 1);
        } else if (deck.getCurrentlyViewing().getCard().getSide()) {
            displayArea = new JTextArea("Question: " + deck.getCurrentlyViewing().getCard().getQuestion(), 4,1);
        } else {
            displayArea = new JTextArea("Answer: " + deck.getCurrentlyViewing().getCard().getAnswer(),4,1);
        }

        displayArea.setFont(new Font("Helvetica", Font.PLAIN, 25));
        displayArea.setSize(new Dimension(600, 350));

        displayArea.setLineWrap(true);

        deckPanelBody.add(displayArea);
        deckPanelBody.revalidate();
        deckPanelBody.repaint();
    }

    // MODIFIES: this
    // EFFECTS: displays the next card in the deck
    private void nextCard() {
        if (deckInView.getCurrentlyViewing() != null) {
            deckInView.getCurrentlyViewing().getCard().flipOntoQuestionSide();
            deckInView.setCurrentlyViewing(deckInView.getCurrentlyViewing().getNextDeckNode());
        }
        if (displayArea != null) {
            deckPanelBody.remove(displayArea);
        }
        cardDisplay(deckInView);
    }

    // MODIFIES: this
    // EFFECTS: displays the previous card in the deck
    private void previousCard() {
        if (deckInView.getCurrentlyViewing() != null) {
            deckInView.getCurrentlyViewing().getCard().flipOntoQuestionSide();
            deckInView.setCurrentlyViewing(deckInView.getCurrentlyViewing().getPrevDeckNode());
        }
        if (displayArea != null) {
            deckPanelBody.remove(displayArea);
        }
        cardDisplay(deckInView);
    }

    // MODIFIES: this
    // EFFECTS: flips the viewed card from question to answer side or answer to question side
    private void flipCard() {
        if (deckInView.getCurrentlyViewing() != null) {
            deckInView.getCurrentlyViewing().getCard().flipCard();
        }
        if (displayArea != null) {
            deckPanelBody.remove(displayArea);
        }
        cardDisplay(deckInView);
    }

    // MODIFIES: this
    // EFFECTS: deletes the viewed card from the current deck in view
    private void deleteCard() {
        if (deckInView.getCurrentlyViewing() != null) {
            deckInView.deleteDeckNode(deckInView.getCurrentlyViewing());
        }
        if (displayArea != null) {
            deckPanelBody.remove(displayArea);
        }
        cardDisplay(deckInView);
    }

    // MODIFIES: this
    // EFFECTS: creates the panel for adding a new card
    private void addCardPanel() {
        deckPanel.setVisible(false);
        resetSmallSize();

        addCardPanel = new JPanel();
        addCardPanel.setBackground(Color.GRAY);

        FlowLayout addCardPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 25);
        addCardPanel.setLayout(addCardPanelLayout);
        enterNewQuestion = new JLabel("Enter Question:");
        enterNewAnswer = new JLabel("Enter Answer:");
        styleLabel(enterNewQuestion);
        styleLabel(enterNewAnswer);
        newQuestion = new JTextField(60);
        newAnswer = new JTextField(60);

        addCardPanel.add(enterNewQuestion);
        addCardPanel.add(newQuestion);
        addCardPanel.add(enterNewAnswer);
        addCardPanel.add(newAnswer);

        addAddCardButton();

        add(addCardPanel);
        addCardPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the button for adding a new card
    private void addAddCardButton() {
        makeCardButton = new JButton("Add card");
        styleButton(makeCardButton);
        makeCardButton.setActionCommand("add card");
        makeCardButton.addActionListener(this);
        addCardPanel.add(makeCardButton);
    }

    // MODIFIES: this
    // EFFECTS: adds a new card to the deck based on the question and answer written by the user
    private void addCard() {
        question = newQuestion.getText();
        answer = newAnswer.getText();

        if (question.isEmpty() || answer.isEmpty()) {
            String emptyTextField = "Please enter a question and an answer!";
            popUpMessage(emptyTextField, addCardPanel);
        } else {
            Deck deck = deckInView;
            DeckNode newDeckNode = new DeckNode(new Card(question, answer));
            deck.addDeckNode(newDeckNode);
            deck.setCurrentlyViewing(newDeckNode);
            makeNewDeckPanel(deck, deckNameInView);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the panel for users to edit a specific card
    private void editCardPanel() {
        if (!deckInView.getDeckNodes().isEmpty()) {
            deckPanel.setVisible(false);
            resetSmallSize();

            editCardPanel = new JPanel();
            editCardPanel.setBackground(Color.GRAY);

            FlowLayout editCardPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 25);
            editCardPanel.setLayout(editCardPanelLayout);
            enterEditedQuestion = new JLabel("Enter New Question:");
            enterEditedAnswer = new JLabel("Enter New Answer:");
            styleLabel(enterEditedQuestion);
            styleLabel(enterEditedAnswer);
            editedQuestion = new JTextField(60);
            editedAnswer = new JTextField(60);

            editCardPanel.add(enterEditedQuestion);
            editCardPanel.add(editedQuestion);
            editCardPanel.add(enterEditedAnswer);
            editCardPanel.add(editedAnswer);

            addEditCardButton();

            add(editCardPanel);
            editCardPanel.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the button for editing a specific card
    private void addEditCardButton() {
        editCardButton = new JButton("Edit card");
        styleButton(editCardButton);
        editCardButton.setActionCommand("edit card");
        editCardButton.addActionListener(this);
        editCardPanel.add(editCardButton);
    }

    // MODIFIES: this
    // EFFECTS: edits the current card and replaces it with a new question and answer
    private void editCard() {
        questionString = editedQuestion.getText();
        answerString = editedAnswer.getText();

        if (questionString.isEmpty() || answerString.isEmpty()) {
            String emptyTextField = "Please enter a new question and a new answer!";
            popUpMessage(emptyTextField, editCardPanel);
        } else {
            Deck deck = deckInView;
            deck.editDeckNode(deck.getCurrentlyViewing(), questionString, answerString);
            makeNewDeckPanel(deck, deckNameInView);
        }
    }

    // MODIFIES: this
    // EFFECTS: returns to the main menu panel and closes all other panels
    private void returnToMainMenu() {
        resetSize();
        if (creationPanel != null) {
            creationPanel.setVisible(false);
        }
        if (removalPanel != null) {
            removalPanel.setVisible(false);
        }
        if (deckPanel != null) {
            deckPanel.setVisible(false);
        }
        for (Deck deck : decks.getDecks().values()) {
            if (!deck.getDeckNodes().isEmpty()) {
                deck.flipAllCardsBackToQuestionSide();
            }
        }
        initializeDecksMenu();
    }

    // MODIFIES: this
    // EFFECTS: creates a popUp image when the user exits the application
    private void popUpImage() {
        closeAllPanels();

        popUp = new JPanel();
        popUp.setMinimumSize(new Dimension(600,400));
        popUp.setMaximumSize(new Dimension(600,400));
        popUp.setSize(new Dimension(600,400));

        popUp.setBackground(Color.GRAY);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 20, 50);
        popUp.setLayout(flowLayout);

        addLabelsToPopUp();

        add(popUp);
        popUp.setVisible(true);
        setVisible(true);

        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                popUp.setVisible(false);
                System.out.println("Goodbye!");
                System.out.println("\n");
                printEventLog();
                System.exit(0);
            }
        };
        timer.schedule(task, 2000);
    }

    // MODIFIES: this
    // EFFECTS: adds labels to pop up
    private void addLabelsToPopUp() {
        JLabel goodbyeMessage = new JLabel("Goodbye!");
        goodbyeMessage.setFont(new Font("Helvetica", Font.BOLD, 40));

        ImageIcon image = new ImageIcon("./data/Flashcards.jpg");
        JLabel flashCards = new JLabel(image);

        popUp.add(goodbyeMessage);
        popUp.add(flashCards);
    }

    private void printEventLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDescription());
            System.out.println(e.getDate());
            System.out.println("\n");
        }
    }
}
