package model.game;

import model.deck.DeckManager;
import model.participant.Dealer;
import model.participant.Player;
import model.result.GameResults;

import java.util.*;
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

    public boolean carPlayerDraw(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .map(Player::canDraw)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어 이름을 찾을 수 없습니다."));
    }

    public void giveCardTo(String playerName) {
        Player findPlayer = players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 이름의 플레이어를 찾을 수 없습니다."));

        findPlayer.receive(deckManager.drawCard());
    }

    public boolean isDealerDrawingRequired() {
        return dealer.isDrawingRequired();
    }

    public void giveCardToDealer() {
        dealer.receive(deckManager.drawCard());
    }

    public Map<String, List<String>> getCardsOf(String playerName) {
        Player player = players.stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어 이름을 찾을 수 없습니다."));

        return Map.of(player.getName(), player.getCards());
    }

    public List<String> getDealerCards() {
        return dealer.getCards();
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

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Map<String, Integer> getPlayerTotalValue() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getTotalValue
                ));
    }

    public int getDealerTotalValue() {
        return dealer.getTotalValue();
    }

    public Map<String, String> getPlayersResults(){
        GameResults gameResults = determineResults();
        return gameResults.getPlayerResultsDisplay();
    }

    public List<Integer> getDealerResultSummary(){
        GameResults gameResults = determineResults();
        return gameResults.getDealerResultSummary();
    }

    private GameResults determineResults() {
        return GameResults.of(players, dealer);
    }
}
