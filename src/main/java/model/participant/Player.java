package model.participant;

import model.card.Card;
import model.card.Cards;

public class Player extends Participant {
    private static final int CAN_DRAW = 21;
    private static final int BUST = 22;

    private final Name name;

    private Player(Name name) {
        super(new Cards());
        this.name = name;
    }

    public Player(Name name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public static Player create(String name){
        return new Player(new Name(name));
    }

    public Player giveCardIfMatches(String playerName, Card card) {
        if (hasName(playerName)) {
            return receive(card);
        }
        return this;
    }

    public boolean hasName(String playerName) {
        return name.isSame(playerName);
    }

    @Override
    public Player receive(Card draw) {
        return new Player(name, cards.add(draw));
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
