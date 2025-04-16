package model.participant;

import model.card.Card;
import model.card.Cards;

import java.util.List;

public class Player extends Participant {
    private static final int CAN_DRAW = 21;
    private static final int BUST = 22;

    private final Name name;

    private Player(Name name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public static Player receiveInitialCard(String name, List<Card> cards) {
        return new Player(new Name(name), new Cards(cards));
    }

    public boolean canDraw() {
        return cards.calculateScore() <= CAN_DRAW;
    }

    public String getName() {
        return name.getName();
    }

    @Override
    protected int bustThreshold() {
        return BUST;
    }
}
