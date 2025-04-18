package model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackGameTest {

    private List<String> playerNames;
    private Map<String, Integer> playerNameToBetMoney;

    @BeforeEach
    void setUp(){String playerNameA = "양";
        String playerNameB = "준";
        playerNames = List.of(playerNameA, playerNameB);
        playerNameToBetMoney = Map.of(
                playerNameA, 10000,
                playerNameB, 20000
        );
    }

    @DisplayName("게임 생성 시 딜러는 2장의 카드를 지급받는다")
    @Test
    void dealerReceiveInitialCard() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames, playerNameToBetMoney);

        //when
        List<String> dealerCards = blackjackGame.getDealerCards();

        // then

        assertThat(dealerCards.size()).isEqualTo(2);
    }

    @DisplayName("게임 생성 시 플레이어들은 2장의 카드를 지급받는다")
    @Test
    void playersReceiveInitialCard() {
        // given
        String playerNameA = playerNames.get(0);
        String playerNameB = playerNames.get(1);
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames, playerNameToBetMoney);

        // when
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
        String playerNameA = playerNames.get(0);
        String playerNameB = playerNames.get(1);
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames, playerNameToBetMoney);

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