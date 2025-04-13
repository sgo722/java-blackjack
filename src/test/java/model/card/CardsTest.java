package model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardsTest {

    @DisplayName("A 한 장일 경우 11점")
    @Test
    void aceReturn11() {
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.ACE)
        ));
        assertThat(cards.calculateScore()).isEqualTo(11);
    }

    @DisplayName("A와 8은 총합 19점")
    @Test
    void aceEightReturn19() {
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.EIGHT)
        ));
        assertThat(cards.calculateScore()).isEqualTo(19);
    }

    @DisplayName("A, 8, 2는 A를 1로 계산해서 21점")
    @Test
    void aceEightTwoReturn21() {
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.EIGHT),
                new Card(Suit.CLUB, Rank.TWO)
        ));
        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A와 K는 블랙잭 21점")
    @Test
    void aceKingReturn21() {
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.KING)
        ));
        assertThat(cards.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A 두 장과 5는 A를 1과 11로 계산해서 17점")
    @Test
    void aceAceFiveReturn21() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.CLUB, Rank.FIVE)
        ));

        //when - then
        assertThat(cards.calculateScore()).isEqualTo(17);
    }

    @DisplayName("숫자 카드만 있을 경우 정수 합산")
    @Test
    void twoThreeFourReturn9() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.HEART, Rank.THREE),
                new Card(Suit.CLUB, Rank.FOUR)
        ));

        //when - then
        assertThat(cards.calculateScore()).isEqualTo(9);
    }
}