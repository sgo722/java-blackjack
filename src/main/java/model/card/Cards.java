package model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> toDisplay(){
        return cards.stream()
                .map(Card::toDisplay)
                .collect(Collectors.toList());
    }

    public int size(){
        return cards.size();
    }
}
