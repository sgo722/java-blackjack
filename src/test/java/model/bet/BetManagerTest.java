package model.bet;

import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BetManagerTest {

    private Players players;
    private Dealer dealer;
    private Map<String, Integer> betMap;

    @BeforeEach
    void setUp() {
        Player pobi = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.JACK) // blackjack
        ));
        Player jason = Player.receiveInitialCard("jun", List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.TEN) // 20
        ));
        Player woody = Player.receiveInitialCard("yeong", List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.SEVEN) // 17
        ));

        players = Players.from(List.of(pobi, jason, woody));

        dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.CLUB, Rank.SIX) // 16
        ));

        betMap = Map.of(
                "yang", 1_000,
                "jun", 2_000,
                "yeong", 3_000
        );
    }

    @DisplayName("플레이어 각각의 수익이 올바르게 계산되는지 확인한다")
    @Test
    void calculatePlayerEarning() {
        //given
        BetManager betManager = BetManager.from(betMap);

        //when
        Map<String, Integer> result = betManager.getPlayerNameToEarningAmount(players, dealer);

        //then
        assertAll(
                () -> Assertions.assertThat(result.get("yang")).isEqualTo(1500),  // 블랙잭 1.5배
                () -> assertThat(result.get("jun")).isEqualTo(2000),  // 승리
                () -> assertThat(result.get("yeong")).isEqualTo(3000)  // 승리 (딜러가 16, 플레이어 17)
        );
    }

    @DisplayName("딜러는 플레이어들의 수익만큼 손해를 본다")
    @Test
    void calculateDealerEarning() {
        //given
        BetManager betManager = BetManager.from(betMap);

        //when
        int dealerEarning = betManager.calculateDealerEarning(players, dealer);

        //then -  yang: -1500, jun: -2000, yeong: -3000
        assertThat(dealerEarning).isEqualTo(-6500);
    }
}
