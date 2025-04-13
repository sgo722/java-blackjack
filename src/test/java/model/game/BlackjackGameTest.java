package model.game;

import model.card.Cards;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Name;
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
        List<Player> dealtPlayers = blackjackGame.getPlayers();

        assertThat(dealer.getCards().getCardList()).hasSize(2);

        for (Player player : dealtPlayers) {
            assertThat(player.getCards().getCardList()).hasSize(2);
        }
    }

    @DisplayName("추가 카드 수령 시 카드 수가 늘어난다")
    @Test
    void drawMoreCard(){
        //given
        String playerName = "양";

        List<String> playerNames = List.of(playerName);
        Players players = Players.fromNames(playerNames);
        Deck deck = new Deck();
        BlackjackGame blackjackGame = BlackjackGame.create(players, deck);
        //when
        BlackjackGame drawBlackjackGame = blackjackGame.giveCardToPlayer(playerName);
        //then
        assertThat(drawBlackjackGame.findPlayer(playerName).getCards().getCardList()).hasSize(3);
    }
}