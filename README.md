# My Personal Project: Flashcard Review System

## About the Project

This application is meant to help a user with studying a certain topic through the use of flashcards.
Each flashcard will have a question side and an answer side, where the user can try to solve the
question before looking at the answer. Other features include being able to **create** cards, **delete** cards,
and **flip through multiple** cards within the deck.

**Students** will be the primary users of this application, since it is meant to help with _memorization_ and
_active recall_, bettering the chances of success in tests and exams. However, anyone who is trying to memorize
information or study a specific topic may also use this application.

This project is of interest to me because when I study for exams, I enjoy using this method, especially
when there is **a lot of information to memorize** or when certain topics seem really **overwhelming**. This way of
studying was very effective for me when I didn't have much time to handwrite notes or memorize paragraphs from
a textbook, since it only requires a few flashcards and a pen. Digitizing this method of studying will make
it even more convenient.

## User Stories:

- As a user, I want to be able to add flashcards to my deck.
- As a user, I want to be able to edit flashcards in my deck.
- As a user, I want to be able to remove flashcards from my deck.
- As a user, I want to be able to view flashcards in my deck.
- As a user, I want to be able to flip flashcards, so that I can view the solution after attempting the question on
  the front side.
- As a user, I want to be able to easily move to the next and previous flashcard in the deck.
- As a user, I want to be able to create multiple decks.
- As a user, I want to be able to save my flashcard decks to file.
- As a user, I want to be given the option to load my flashcard deck from file and resume studying with the flashcard
  decks that I created at an earlier time.

## Instructions for Grader

- You can add and remove multiple decks by clicking the "Create deck" and "Remove deck" buttons in the main menu panel.
  You will then be able to type the name of the deck you want to create or remove in a pop-up window.
- You can also add and remove multiple cards from a single deck by clicking the "Add card", "Remove card", and "Edit
  card" buttons in the deck menu.
- You can save the decks you have created by clicking on the "Save decks to file" button in the main menu, which will
  save all the decks that you have created during the current session.
- You can reload the decks you have created by clicking on the "Load decks from file" button in the main menu, which
  will reload all the decks that you have saved and stored during a previous session.
- You can find the visual component (an image) when you exit the program by clicking the "Quit" button in the main menu.
  Once you do this, you will be presented with a two-second pop-up image of a flashcard deck before the program exits.

## Phase 4: Task 2

- Event log cleared.
- Tue Aug 09 15:34:01 PDT 2022

- New deck, deck1, has been created.
- Tue Aug 09 15:34:08 PDT 2022

- Added card with question 1+1 and answer 2.
- Tue Aug 09 15:34:13 PDT 2022

- Added card with question 2+2 and answer 4.
- Tue Aug 09 15:34:20 PDT 2022

- Edited card from question 1+1 and answer 2 to question 3+3 and answer 6.
- Tue Aug 09 15:34:26 PDT 2022

- Deleted card with question 2+2 and answer 4.
- Tue Aug 09 15:34:30 PDT 2022

## Phase 4: Task 3

- One improvement I would make to my design would be to eliminate the deckName array in the decks file. Since I used a
  hashmap to store each of the decks under a key corresponding to the specific deck, there was no real point in storing
  the names of all the decks in a separate array when I could just retrieve all the keys (the names of the decks) from the
  hashmap instead.

- Another improvement I would make to my design would be to separate my MainMenu class into multiple classes. The
  MainMenu class is the GUI of my project, and I think it would have been much more readable if I split it up into
  multiple classes rather than trying to compress the entire GUI into a single file. This would increase the cohesion of
  my code and better satisfy the SRP, especially if I had a different class for each menu and pop-up in my GUI (for
  example, one class for the main menu, one class for the panel that displays the deck, one class for the panel for
  displaying the deck, etc.).# My Personal Project: Flashcard Review System

## About the Project

This application is meant to help a user with studying a certain topic through the use of flashcards.
Each flashcard will have a question side and an answer side, where the user can try to solve the
question before looking at the answer. Other features include being able to **create** cards, **delete** cards,
and **flip through multiple** cards within the deck.

**Students** will be the primary users of this application, since it is meant to help with _memorization_ and
_active recall_, bettering the chances of success in tests and exams. However, anyone who is trying to memorize
information or study a specific topic may also use this application.

This project is of interest to me because when I study for exams, I enjoy using this method, especially
when there is **a lot of information to memorize** or when certain topics seem really **overwhelming**. This way of
studying was very effective for me when I didn't have much time to handwrite notes or memorize paragraphs from
a textbook, since it only requires a few flashcards and a pen. Digitizing this method of studying will make
it even more convenient.

## User Stories:

- As a user, I want to be able to add flashcards to my deck.
- As a user, I want to be able to edit flashcards in my deck.
- As a user, I want to be able to remove flashcards from my deck.
- As a user, I want to be able to view flashcards in my deck.
- As a user, I want to be able to flip flashcards, so that I can view the solution after attempting the question on
  the front side.
- As a user, I want to be able to easily move to the next and previous flashcard in the deck.
- As a user, I want to be able to create multiple decks.
- As a user, I want to be able to save my flashcard decks to file.
- As a user, I want to be given the option to load my flashcard deck from file and resume studying with the flashcard
  decks that I created at an earlier time.

## Instructions for Grader

- You can add and remove multiple decks by clicking the "Create deck" and "Remove deck" buttons in the main menu panel.
  You will then be able to type the name of the deck you want to create or remove in a pop-up window.
- You can also add and remove multiple cards from a single deck by clicking the "Add card", "Remove card", and "Edit
  card" buttons in the deck menu.
- You can save the decks you have created by clicking on the "Save decks to file" button in the main menu, which will
  save all the decks that you have created during the current session.
- You can reload the decks you have created by clicking on the "Load decks from file" button in the main menu, which
  will reload all the decks that you have saved and stored during a previous session.
- You can find the visual component (an image) when you exit the program by clicking the "Quit" button in the main menu.
  Once you do this, you will be presented with a two-second pop-up image of a flashcard deck before the program exits.

## Phase 4: Task 2

- Event log cleared.
- Tue Aug 09 15:34:01 PDT 2022

- New deck, deck1, has been created.
- Tue Aug 09 15:34:08 PDT 2022

- Added card with question 1+1 and answer 2.
- Tue Aug 09 15:34:13 PDT 2022

- Added card with question 2+2 and answer 4.
- Tue Aug 09 15:34:20 PDT 2022

- Edited card from question 1+1 and answer 2 to question 3+3 and answer 6.
- Tue Aug 09 15:34:26 PDT 2022

- Deleted card with question 2+2 and answer 4.
- Tue Aug 09 15:34:30 PDT 2022

## Phase 4: Task 3

- One improvement I would make to my design would be to eliminate the deckName array in the decks file. Since I used a
  hashmap to store each of the decks under a key corresponding to the specific deck, there was no real point in storing
  the names of all the decks in a separate array when I could just retrieve all the keys (the names of the decks) from the
  hashmap instead.

- Another improvement I would make to my design would be to separate my MainMenu class into multiple classes. The
  MainMenu class is the GUI of my project, and I think it would have been much more readable if I split it up into
  multiple classes rather than trying to compress the entire GUI into a single file. This would increase the cohesion of
  my code and better satisfy the SRP, especially if I had a different class for each menu and pop-up in my GUI (for
  example, one class for the main menu, one class for the panel that displays the deck, one class for the panel for
  displaying the deck, etc.).
