package model.game;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;
import model.result.GameResult;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    // 블랙잭 결과를 비교한다.
    private final Participants participants;
    private final Deck deck;

    private BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame create(Players players, Deck deck) {
        Dealer dealer = new Dealer();

        for(int drawCount = 0; drawCount < 2; drawCount++) {
            players = giveCardToPlayers(players, deck);
            dealer = giveCardToDealer(dealer, deck);
        }

        return new BlackjackGame(new Participants(players, dealer), deck);
    }

    private static Players giveCardToPlayers(Players players, Deck deck) {
        List<Player> drawPlayers = new ArrayList<>();

        for(Player player : players.getPlayers()) {
            drawPlayers.add(player.receive(deck.draw()));
        }

        return Players.from(drawPlayers);
    }

    private static Dealer giveCardToDealer(Dealer dealer, Deck deck) {
        return dealer.receive(deck.draw());
    }

    public BlackjackGame giveCardToPlayer(String playerName) {
        return new BlackjackGame(participants.giveCardToPlayer(playerName, deck.draw()),deck);
    }

    public BlackjackGame giveCardToDealer() {
        return new BlackjackGame(participants.giveCardToDealer(deck.draw()), deck);
    }

    public boolean canDraw(String playerName) {
        return participants.canDraw(playerName);
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
        return participants.getPlayers();
    }

    public boolean dealerMustDraw() {
        return participants.dealerMustDraw();
    }
}
