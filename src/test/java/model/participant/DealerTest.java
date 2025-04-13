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

class DealerTest {

    @DisplayName("딜러는 카드를 수령하면 카드가 추가된다")
    @Test
    void receiveCard() {
        //given
        Dealer dealer = new Dealer();
        Card card = new Card(Suit.CLUB, Rank.SIX);

        //when
        Dealer afterDrawDealer = dealer.receive(card);

        //then
        assertAll(
                () -> assertThat(dealer.getCards().getCardList()).isEmpty(),
                () -> assertThat(afterDrawDealer.getCards().getCardList()).hasSize(1)
        );
    }

    @DisplayName("딜러는 카드 점수가 22 이상이면 bust 상태이다")
    @Test
    void dealer_is_bust() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.CLUB, Rank.THREE)
        ));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.isBust()).isTrue();
    }

    @DisplayName("딜러는 카드 점수가 16 이하이면 카드를 무조건 받아야 한다")
    @Test
    void sixteenIsRequiredCard() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.SIX),
                new Card(Suit.CLUB, Rank.TEN)
        ));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.isDrawingRequired()).isTrue();
    }

    @DisplayName("딜러는 카드 점수가 15점이면 받아야 한다")
    @Test
    void fifteenIsRequiredCard() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.FIVE),
                new Card(Suit.CLUB, Rank.TEN)
        ));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.isDrawingRequired()).isTrue();
    }

    @DisplayName("딜러는 카드 점수가 17점이면 받지 않는다")
    @Test
    void seventeenIsNotRequiredCard() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.SEVEN),
                new Card(Suit.CLUB, Rank.TEN)
        ));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.isDrawingRequired()).isFalse();
    }
}