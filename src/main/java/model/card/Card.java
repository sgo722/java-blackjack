package model.card;

public class Card {
    //카드는 문양과 랭크를 가진다.
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isAce(){
        return rank.isAce();
    }

    public String getSuit(){
        return suit.getName();
    }

    public String getRank(){
        return rank.getName();
    }

    public int getValue(){
        return rank.getValue();
    }
}
