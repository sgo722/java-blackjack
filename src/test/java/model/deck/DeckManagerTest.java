package model.deck;

import model.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckManagerTest {

    @DisplayName("카드 두장을 뽑는다")
    @Test
    void drawTwoCard(){
        //given
        DeckManager deckManager = new DeckManager();

        //when
        List<Card> cards = deckManager.drawTwoCard();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

}