package dto;

import model.card.Card;

public class CardDto {
    private String rank;
    private String suit;

    public CardDto(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static CardDto from(Card card){
        return new CardDto(card.getRank(), card.getSuit());
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}
