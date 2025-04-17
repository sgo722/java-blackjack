package model.participant;

import model.card.Card;
import model.card.Cards;

import java.util.List;

public abstract class Participant {
    static final int BLACK_JACK = 21;
    protected Cards cards;

    protected Participant(Cards cards) {
        this.cards = cards;
    }

    public List<String> getCards() {
        return cards.toDisplay();
    }

    public int getTotalValue() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.calculateScore() >= bustThreshold();
    }

    public void receive(Card draw) {
        cards = cards.add(draw);
    }

    public boolean isBlackjack(){ return cards.calculateScore() == BLACK_JACK;}

    protected abstract int bustThreshold();
}
