package model.participant;

import model.card.Card;
import model.card.Cards;

import java.util.List;

public class Dealer extends Participant {
    // 카드의 합이 16이하라면 카드를 뽑는다.
    private static final int CAN_DRAW = 16;
    private static final int BUST = 22;

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

    public Cards getCards() {
        return cards;
    }

    public boolean canDraw() {
        return cards.calculateScore() <= CAN_DRAW;
    }

    public int getTotalValue(){
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.calculateScore() >= BUST;
    }
}
