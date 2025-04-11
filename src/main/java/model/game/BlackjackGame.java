package model.game;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    // 블랙잭 결과를 비교한다.
    private final Deck deck;
    private final Participants participants;

    private BlackjackGame(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackGame create(Players players, Deck deck) {
        Dealer dealer = new Dealer();

        for(int drawCount = 0; drawCount < 2; drawCount++) {
            players = drawCardToPlayers(players, deck);
            dealer = drawCardToDealer(dealer, deck);
        }

        return new BlackjackGame(deck, new Participants(players, dealer));
    }

    private static Players drawCardToPlayers(Players players, Deck deck) {
        List<Player> drawPlayers = new ArrayList<>();

        for(Player player : players.getPlayers()) {
            drawPlayers.add(player.receive(deck.draw()));
        }

        return Players.from(drawPlayers);
    }

    private static Dealer drawCardToDealer(Dealer dealer, Deck deck) {
        return dealer.receive(deck.draw());
    }

    public Participants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public Players getPlayers(){
        return participants.getPlayers();
    }
}
