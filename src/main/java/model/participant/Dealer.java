package model.participant;

import model.card.Card;
import model.card.Cards;

import java.util.List;

public class Dealer extends Participant {
    // 카드의 합이 16이하라면 카드를 뽑는다.
    private final Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public Dealer receive(Card draw) {
        return new Dealer(cards.add(draw));
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
