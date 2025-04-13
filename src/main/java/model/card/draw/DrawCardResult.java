package model.card.draw;

import model.card.Card;
import model.card.Deck;

public record DrawCardResult(Card card, Deck nextDeck) {
}
