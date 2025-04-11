package model.card;

public class Card {
    //카드는 문양과 랭크를 가진다.
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
}
