package model.bet;

import model.bet.BetPolicy;
import model.bet.DefaultBetPolicy;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultBetPolicyTest {

    private BetPolicy policy = new DefaultBetPolicy();

    @Test
    @DisplayName("플레이어가 초기 블랙잭이면 배팅 금액의 1.5배를 얻는다")
    void playerInitialBlackjack() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.JACK)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(1500);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이면 배팅 금액만큼 돌려받는다")
    void bothBlackjack() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.JACK),
                new Card(Suit.CLUB, Rank.ACE)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.KING)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 버스트면 배팅 금액을 잃는다")
    void playerBust() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN),
                new Card(Suit.DIAMOND, Rank.TWO)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러가 버스트면 플레이어는 배팅 금액만큼 얻는다")
    void dealerBust() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.HEART, Rank.FOUR)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.KING),
                new Card(Suit.DIAMOND, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.TWO)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어 점수가 높으면 배팅 금액을 얻는다")
    void playerWins() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.NINE)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.EIGHT)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어와 딜러 점수가 같으면 배팅 금액을 돌려받는다")
    void draw() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.SEVEN)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.DIAMOND, Rank.EIGHT)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러 점수가 높으면 플레이어는 배팅 금액을 잃는다")
    void dealerWins() {
        Player player = Player.receiveInitialCard("yang", List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.EIGHT)
        ));
        Dealer dealer = Dealer.receiveInitialCard(List.of(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));

        int result = policy.calculatePlayerEarning(1000, player, dealer);
        assertThat(result).isEqualTo(-1000);
    }
}
