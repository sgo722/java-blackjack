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

    public List<Card> getCardList() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        if (hasAce() && score <= 11) {
            score += 10;
        }

        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
}
