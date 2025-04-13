package model.result;

import model.card.Card;
import model.card.Cards;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Name;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @DisplayName("플레이어가 딜러보다 합이 클 경우 플레이어는 승리한다")
    @Test
    void winByHighScoreThanDealer() {
        //given
        Player player = new Player(new Name("yang"), new Cards(List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.NINE)
        )));
        Dealer dealer = new Dealer(new Cards(List.of(
                new Card(Suit.HEART, Rank.NINE),
                new Card(Suit.CLUB, Rank.NINE)
        )));

        //when
        GameResult result = GameResult.of(player, dealer);

        //then
        assertThat(result.getResult()).isEqualTo("승");
    }

    @DisplayName("플레이어가 딜러보다 합이 작을 경우 플레이어는 패배한다")
    @Test
    void loseByLowScoreThanDealer() {
        //given
        Player player = new Player(new Name("yang"), new Cards(List.of(
                new Card(Suit.HEART, Rank.FOUR),
                new Card(Suit.SPADE, Rank.SIX) // 합계 10
        )));
        Dealer dealer = new Dealer(new Cards(List.of(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.EIGHT) // 합계 16
        )));

        //when
        GameResult result = GameResult.of(player, dealer);

        //then
        assertThat(result.getResult()).isEqualTo("패");
    }

    @DisplayName("플레이어와 딜러의 카드 합이 같을 경우 플레이어는 무승부한다")
    @Test
    void draw() {
        //given
        Player player = new Player(new Name("yang"), new Cards(List.of(
                new Card(Suit.HEART, Rank.NINE),
                new Card(Suit.SPADE, Rank.TEN)
        )));
        Dealer dealer = new Dealer(new Cards(List.of(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.ACE)
        )));

        //when
        GameResult result = GameResult.of(player, dealer);

        //then
        assertThat(result.getResult()).isEqualTo("무");
    }

    @DisplayName("플레이어는 생존상태이고 딜러가 버스트된 경우 플레이어가 승리한다")
    @Test
    void winByOnlyDealerBust() {
        //given
        Player player = new Player(new Name("yang"), new Cards(List.of(
                new Card(Suit.HEART, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.TEN)
        )));
        Dealer dealer = new Dealer(new Cards(List.of(
                new Card(Suit.CLUB, Rank.KING),
                new Card(Suit.DIAMOND, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.THREE)
        )));

        //when
        GameResult result = GameResult.of(player, dealer);


        //then
        assertThat(result.getResult()).isEqualTo("승");
    }

    @DisplayName("플레이어와 딜러 모두 버스트된 경우 플레이어가 패배한다")
    @Test
    void loseByAllBust() {
        //given
        Player player = new Player(new Name("yang"), new Cards(List.of(
                new Card(Suit.CLUB, Rank.KING),
                new Card(Suit.DIAMOND, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.THREE)
        )));
        Dealer dealer = new Dealer(new Cards(List.of(
                new Card(Suit.CLUB, Rank.KING),
                new Card(Suit.DIAMOND, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.THREE)
        )));

        //when
        GameResult result = GameResult.of(player, dealer);

        //then
        assertThat(result.getResult()).isEqualTo("패");
    }

}