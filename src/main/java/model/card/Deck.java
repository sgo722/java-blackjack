package model.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    public Deck() {
        this.deck = generateDeck();
    }

    public Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    private Stack<Card> generateDeck() {
        Stack<Card> deck = new Stack<>();

        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }

        Collections.shuffle(deck);
        return deck;
    }

    public Card draw() {
        return deck.pop();
    }
}
