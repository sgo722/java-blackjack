package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    // 카드 일급 컬렉션
    // 카드를 뽑는다.
    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Cards add(Card drawCard) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(drawCard);

        return new Cards(newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
