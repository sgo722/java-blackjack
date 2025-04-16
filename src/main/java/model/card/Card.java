package model.card;

public class Card {
    //카드는 문양과 랭크를 가진다.
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isAce(){
        return rank.isAce();
    }

    public int getValue(){
        return rank.getValue();
    }

    public String toDisplay(){
        return rank.getSymbol() + suit.getName();
    }
}
