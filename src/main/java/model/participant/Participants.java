package model.participant;

import model.card.Card;

import java.util.List;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Participants giveCardToPlayer(String playerName, Card card) {
        return new Participants(players.giveCardTo(playerName, card), dealer);
    }

    public Participants giveCardToDealer(Card card) {
        return new Participants(players, dealer.receive(card));
    }
}
