package dto;

import model.card.Card;
import model.card.Cards;

import java.util.ArrayList;
import java.util.List;

public class CardsDto {
    private final List<CardDto> cards;
    private final int totalValue;

    private CardsDto(List<CardDto> cards, int totalValue) {
        this.cards = cards;
        this.totalValue = totalValue;
    }

    public static CardsDto fromCards(Cards cards) {

        List<CardDto> cardDtos = new ArrayList<>();
        for(Card card : cards.getCardList()){
            cardDtos.add(CardDto.from(card));
        }

        return new CardsDto(cardDtos, cards.calculateScore());
    }

    public List<CardDto> getCardDtoList() {
        return cards;
    }

    public int getTotalValue() {
        return totalValue;
    }
}
