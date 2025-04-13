package model.participant;

import model.card.Card;
import model.card.Cards;

public abstract class Participant {
    protected final Cards cards;

    protected Participant(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }

    public int getTotalValue() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.calculateScore() >= bustThreshold();
    }

    protected abstract int bustThreshold();

    public abstract Participant receive(Card card);
}
