package model.participant;

import model.card.Card;
import model.card.Cards;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerTest {

    @DisplayName("플레이어가 카드를 수령하면 새로운 플레이어 카드가 추가된다")
    @Test
    void receiveCard() {
        Player player = Player.create("jun");
        Card card = new Card(Suit.SPADE, Rank.FIVE);

        Player afterDrawPlayer = player.receive(card);

        assertAll(
                () -> assertThat(player.getCards().getCardList()).isEmpty(),
                () -> assertThat(afterDrawPlayer.getCards().getCardList()).hasSize(1),
                () -> assertThat(afterDrawPlayer.getTotalValue()).isEqualTo(5)
        );
    }

    @DisplayName("플레이어 카드 점수가 22 이상이면 bust 상태이다")
    @Test
    void bust() {
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.KING),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.DIAMOND, Rank.TWO)
        ));
        Player player = new Player(new Name("jun"), cards);

        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("플레이어 카드 점수가 21 이하일 때 추가 카드를 받을 수 있다")
    @Test
    void twentyOneCanDraw() {
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.NINE),
                new Card(Suit.SPADE, Rank.ACE)
        ));
        Player player = new Player(new Name("jun"), cards);

        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어 카드 점수가 20이면 받을 수 있다.")
    @Test
    void twentyCanDraw() {
        Cards cards = new Cards(List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.SPADE, Rank.TEN)
        ));
        Player player = new Player(new Name("jun"), cards);

        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어 카드 점수가 22이면 받을 수 없다")
    @Test
    void twentyTwoCanNotDraw() {
        Cards cards = new Cards(List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TWO)
        ));
        Player player = new Player(new Name("jun"), cards);

        assertThat(player.canDraw()).isFalse();
    }
}