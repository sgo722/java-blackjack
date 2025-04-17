package model.game;

import model.bet.BetManager;
import model.deck.DeckManager;
import model.participant.Dealer;
import model.participant.Players;
import model.result.GameResults;

import java.util.*;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;
    private final BetManager betManager;
    private final DeckManager deckManager;

    private BlackjackGame(Players players, Dealer dealer, BetManager betManager, DeckManager deckManager) {
        this.players = players;
        this.dealer = dealer;
        this.betManager = betManager;
        this.deckManager = deckManager;
    }

    public static BlackjackGame splitInitialCard(List<String> playerNames, Map<String, Integer> playerNameToBetMoney) {
        DeckManager deckManager = new DeckManager();
        Players players = Players.fromNames(playerNames);
        BetManager betManager = BetManager.from(playerNameToBetMoney);

        splitInitialCardToPlayers(players, deckManager);
        Dealer dealer = splitInitialCardToDealer(deckManager);

        return new BlackjackGame(players, dealer, betManager, deckManager);
    }

    private static void splitInitialCardToPlayers(Players players, DeckManager deckManager) {
        for(String playerName : players.getNames()) {
            players.receiveInitialCardTo(playerName, deckManager.drawTwoCard());
        }
    }

    private static Dealer splitInitialCardToDealer(DeckManager deckManager) {
        return Dealer.receiveInitialCard(deckManager.drawTwoCard());
    }

    public void giveCardTo(String playerName) {
        players.giveCardTo(playerName, deckManager.drawCard());
    }

    public void giveCardToDealer() {
        dealer.receive(deckManager.drawCard());
    }

    public boolean canPlayerDraw(String playerName) {
        return players.canDraw(playerName);
    }

    public boolean isDealerDrawingRequired() {
        return dealer.isDrawingRequired();
    }


    public Map<String, List<String>> getPlayerNameToCards() {
        return players.getNameToCards();
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public Map<String, Integer> getPlayerTotalValue() {
        return players.getTotalValue();
    }

    public int getDealerTotalValue() {
        return dealer.getTotalValue();
    }

    public Map<String, List<String>> getCardsOf(String playerName) {
        return players.getCardsOf(playerName);
    }

    public List<String> getDealerCards() {
        return dealer.getCards();
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
