package model.participant;

import model.card.Card;
import model.card.Cards;

import java.util.List;

public class Dealer extends Participant {
    private static final int CAN_DRAW = 16;
    private static final int BUST = 22;

    public Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer receiveInitialCard(List<Card> cards){
        return new Dealer(new Cards(cards));
    }

    @Override
    public void receive(Card draw) {
        cards.add(draw);
    }

    public boolean isDrawingRequired() {
        return cards.calculateScore() <= CAN_DRAW;
    }

    @Override
    protected int bustThreshold() {
        return BUST;
    }
}
