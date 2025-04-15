package model.deck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class DeckTest {

    @DisplayName("덱은 처음에 52장이 있다")
    @Test
    void fullDeck(){
        //given
        Deck deck = new Deck();

        //when - then
        assertThat(deck.size()).isEqualTo(52);
    }

    @DisplayName("덱에서 한장을 뽑으면 카드가 줄어든다")
    @Test
    void draw(){
        //given
        Deck deck = new Deck();

        //when
        deck.draw();

        // then
        assertThat(deck.size()).isEqualTo(51);
    }
}