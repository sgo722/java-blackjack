package model.deck;

import model.card.Card;
import model.card.Rank;
import model.card.Suit;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> deck;

    public Deck() {
        this.deck = generateDeck();
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
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드가 부족합니다.");
        }
        return deck.pop();
    }

    public int size(){
        return deck.size();
    }
}
