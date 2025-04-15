package model.game;

import model.deck.DeckManager;
import model.participant.Dealer;
import model.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final List<Player> players;
    private final Dealer dealer;
    private final DeckManager deckManager;

    private BlackjackGame(List<Player> players, Dealer dealer, DeckManager deckManager) {
        this.players = players;
        this.dealer = dealer;
        this.deckManager = deckManager;
    }

    public static BlackjackGame splitInitialCard(List<String> playerNames) {
        DeckManager deckManager = new DeckManager();
        List<Player> players = splitInitialCardToPlayers(playerNames, deckManager);
        Dealer dealer = splitInitialCardToDealer(deckManager);

        return new BlackjackGame(players, dealer, deckManager);
    }

    private static List<Player> splitInitialCardToPlayers(List<String> playerNames, DeckManager deckManager) {
        return playerNames
                .stream()
                .map(name -> Player.receiveInitialCard(name, deckManager.drawTwoCard()))
                .collect(Collectors.toList());
    }

    private static Dealer splitInitialCardToDealer(DeckManager deckManager) {
        return Dealer.receiveInitialCard(deckManager.drawTwoCard());
    }

    public List<String> getPlayers() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Map<String, List<String>> getPlayersCards() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getCards,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public List<String> getDealerCards() {
        return dealer.getCards();
    }
}
