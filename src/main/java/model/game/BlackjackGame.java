package model.game;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;

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
            drawPlayers(players, deck);
            drawDealer(dealer, deck);
        }

        return new BlackjackGame(deck, new Participants(players, dealer));
    }

    private static void drawPlayers(Players players, Deck deck) {
        for(Player player : players.getPlayers()) {
            player.receive(deck.draw());
        }
    }

    private static void drawDealer(Dealer dealer, Deck deck) {
        dealer.receive(deck.draw());
    }
}
