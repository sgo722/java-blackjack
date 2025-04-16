package model.participant;

import model.card.Card;
import model.card.Cards;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerTest {

    @DisplayName("플레이어가 카드를 수령하면 새로운 플레이어 카드가 추가된다")
    @Test
    void receiveCard() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Rank.KING),
                new Card(Suit.SPADE, Rank.QUEEN)
        );
        Player player = Player.receiveInitialCard("jun", cards);
        Card card = new Card(Suit.SPADE, Rank.FIVE);

        //when
        player.receive(card);

        //then
        assertAll(
                () -> assertThat(player.getCards()).hasSize(3),
                () -> assertThat(player.getTotalValue()).isEqualTo(25)
        );
    }

    @DisplayName("플레이어 카드 점수가 22 이상이면 bust 상태이다")
    @Test
    void bust() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Rank.KING),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.DIAMOND, Rank.TWO)
        );

        //when
        Player player = Player.receiveInitialCard("jun", cards);

        //then
        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("플레이어 카드 점수가 21 이하일 때 추가 카드를 받을 수 있다")
    @Test
    void twentyOneCanDraw() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Rank.NINE),
                new Card(Suit.SPADE, Rank.ACE)
        );

        //when
        Player player = Player.receiveInitialCard("jun", cards);

        //then
        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어 카드 점수가 20이면 받을 수 있다.")
    @Test
    void twentyCanDraw() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.SPADE, Rank.TEN)
        );

        //when
        Player player = Player.receiveInitialCard("jun", cards);

        //then
        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어 카드 점수가 22이면 받을 수 없다")
    @Test
    void twentyTwoCanNotDraw() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TWO)
        );

        //when
        Player player = Player.receiveInitialCard("jun", cards);

        //then
        assertThat(player.canDraw()).isFalse();
    }
}