package model.card.draw;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Players;

public record DrawInitialResult(Players players, Deck newDeck, Dealer dealer) {
}
