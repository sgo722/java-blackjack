package model.deck;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class DeckManager {
    private static final int INIT_CARD_COUNT = 2;

    private final Deck deck;

    public DeckManager() {
        this.deck = new Deck();
    }

    public Card drawCard(){
        return deck.draw();
    }

    public List<Card> drawTwoCard() {
        List<Card> cards = new ArrayList<>();
        for (int count = 0; count < INIT_CARD_COUNT; count++) {
            cards.add(deck.draw());
        }
        return cards;
    }
}
