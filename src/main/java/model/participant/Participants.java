package model.participant;

import model.card.Card;
import model.result.GameResult;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Participants giveCardToPlayer(String playerName, Card card) {
        return new Participants(players.giveCardTo(playerName, card), dealer);
    }

    public Participants giveCardToDealer(Card card) {
        return new Participants(players, dealer.receive(card));
    }

    public List<GameResult> determineResults() {
        List<GameResult> results = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            results.add(GameResult.of(player,dealer));
        }
        return results;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public boolean canDraw(String playerName) {
        return players.canDraw(playerName);
    }

    public Player findPlayer(String playerName) {
        return players.findPlayer(playerName);
    }
}
