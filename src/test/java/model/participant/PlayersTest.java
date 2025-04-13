package model.participant;

import model.card.Card;
import model.card.Cards;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayersTest {

    @DisplayName("플레이어를 이름으로 조회한다")
    @Test
    void findPlayer() {
        //given
        Player playerA = new Player(new Name("yang"), new Cards(List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TEN)
        )));

        Player playerB = new Player(new Name("jun"), new Cards(List.of(
                new Card(Suit.HEART, Rank.THREE),
                new Card(Suit.CLUB, Rank.TWO)
        )));
        Players players = Players.from(List.of(playerA, playerB));
        int totalValue = playerA.getTotalValue();

        //when
        Player findPlayer = players.findPlayer("yang");

        //then
        assertThat(findPlayer.getTotalValue()).isEqualTo(totalValue);
    }

    @DisplayName("이름이 없는 경우 예외를 던진다")
    @Test
    void cannotFindPlayer() {
        //given
        Players players = Players.fromNames(List.of("yang", "jun"));

        //when - then
        assertThrows(IllegalArgumentException.class, () -> players.canDraw("noname"));
    }
}