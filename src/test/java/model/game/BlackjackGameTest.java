package model.game;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
class BlackjackGameTest {

    @DisplayName("게임 생성 시 참여자들은 각각 2장의 카드를 받는다")
    @Test
    void hasOnlyTwoCard() {
        // given
        List<String> playerNames = List.of("양", "준");
        Players players = Players.fromNames(playerNames);
        Deck deck = new Deck();

        // when
        BlackjackGame blackjackGame = BlackjackGame.create(players, deck);

        // then
        Dealer dealer = blackjackGame.getDealer();
        Players dealtPlayers = blackjackGame.getPlayers();

        assertThat(dealer.getCards()).hasSize(2);

        for (Player player : dealtPlayers.getPlayers()) {
            assertThat(player.getCards()).hasSize(2);
        }
    }
  
}