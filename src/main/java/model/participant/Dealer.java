package model.participant;

import model.card.Card;
import model.card.Cards;

public class Dealer extends Participant {
    private static final int CAN_DRAW = 16;
    private static final int BUST = 22;

    public Dealer() {
        super(new Cards());
    }

    public Dealer(Cards cards) {
        super(cards);
    }

    @Override
    public Dealer receive(Card draw) {
        return new Dealer(cards.add(draw));
    }

    public boolean isDrawingRequired() {
        return cards.calculateScore() <= CAN_DRAW;
    }

    @Override
    protected int bustThreshold() {
        return BUST;
    }
}