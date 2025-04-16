package model.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackGameTest {

    @DisplayName("게임 생성 시 딜러는 2장의 카드를 지급받는다")
    @Test
    void dealerReceiveInitialCard() {
        // given
        List<String> playerNames = List.of("양", "준");

        // when
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames);
        List<String> dealerCards = blackjackGame.getDealerCards();

        // then

        assertThat(dealerCards.size()).isEqualTo(2);
    }

    @DisplayName("게임 생성 시 플레이어들은 2장의 카드를 지급받는다")
    @Test
    void playersReceiveInitialCard() {
        // given
        String playerNameA = "양";
        String playerNameB = "준";
        List<String> playerNames = List.of(playerNameA, playerNameB);

        // when
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames);
        Map<String, List<String>> playerNameToCards = blackjackGame.getPlayerNameToCards();

        // then
        assertAll(
            () -> assertThat(playerNameToCards.get(playerNameA).size()).isEqualTo(2),
            () -> assertThat(playerNameToCards.get(playerNameB).size()).isEqualTo(2)
        );
    }

    @DisplayName("추가 카드 수령 시 카드 수가 늘어난다")
    @Test
    void drawMoreCard(){
        //given
        String playerNameA = "양";
        String playerNameB = "준";

        List<String> playerNames = List.of(playerNameA, playerNameB);
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames);
        //when
        blackjackGame.giveCardTo(playerNameA);
        Map<String, List<String>> playerNameToCards = blackjackGame.getPlayerNameToCards();

        //then
        assertAll(
                () -> assertThat(playerNameToCards.get(playerNameA).size()).isEqualTo(3),
                () -> assertThat(playerNameToCards.get(playerNameB).size()).isEqualTo(2)
        );
    }
}