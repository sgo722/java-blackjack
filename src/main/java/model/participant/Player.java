package model.participant;

import model.card.Card;
import model.card.Cards;

public class Player extends Participant {
    // 카드의 합이 21이하라면 카드를 뽑는다.
    private final Name name;
    private final Cards cards;

    private Player(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player create(String name){
        return new Player(new Name(name));
    }

    public String getName() {
        return name.getName();
    }

    public Player receive(Card draw) {
        return new Player(name, cards.add(draw));
    }
}
