package model.game;

import model.card.Deck;
import model.card.draw.DrawInitialResult;
import model.card.draw.DrawPlayersResult;
import model.card.draw.DrawCardResult;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;
import model.result.GameResult;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    // 블랙잭 도메인의 상태변경과 흐름을 조율한다.
    private final Participants participants;
    private final Deck deck;

    private BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame create(Players players) {
        Deck newDeck = new Deck();
        Dealer dealer = new Dealer();

        DrawInitialResult initialResult = drawInitialCardToParticipants(players, newDeck, dealer);

        return new BlackjackGame(new Participants(initialResult.players(), initialResult.dealer()), initialResult.newDeck());
    }

    private static DrawInitialResult drawInitialCardToParticipants(Players players, Deck newDeck, Dealer dealer) {
        for (int drawCount = 0; drawCount < 2; drawCount++) {
            DrawPlayersResult playersResult = dealOneCardToEachPlayer(players, newDeck);
            players = playersResult.players();
            newDeck = playersResult.deck();

            DrawCardResult dealerResult = newDeck.draw();
            dealer = dealer.receive(dealerResult.card());
            newDeck = dealerResult.nextDeck();
        }
        return new DrawInitialResult(players, newDeck, dealer);
    }

    private static DrawPlayersResult dealOneCardToEachPlayer(Players players, Deck deck) {
        List<Player> updatedPlayers = new ArrayList<>();
        Deck currentDeck = deck;

        for (Player player : players.getPlayerList()) {
            DrawCardResult result = currentDeck.draw();
            updatedPlayers.add(player.receive(result.card()));
            currentDeck = result.nextDeck();
        }

        return new DrawPlayersResult(Players.from(updatedPlayers), currentDeck);
    }

    public BlackjackGame giveCardToPlayer(String playerName) {
        DrawCardResult drawCardResult = deck.draw();
        return new BlackjackGame(participants.giveCardToPlayer(playerName, drawCardResult.card()), drawCardResult.nextDeck());
    }

    public BlackjackGame giveCardToDealer() {
        DrawCardResult drawCardResult = deck.draw();
        return new BlackjackGame(participants.giveCardToDealer(drawCardResult.card()), drawCardResult.nextDeck());
    }

    public boolean canDraw(String playerName) {
        return participants.canDraw(playerName);
    }

    public boolean dealerMustDraw() {
        return participants.dealerMustDraw();
    }

    public Player findPlayer(String playerName) {
        return participants.findPlayer(playerName);
    }

    public List<GameResult> getResult() {
        return participants.determineResults();
    }

    public Participants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers(){
        return participants.getPlayerList();
    }
}
