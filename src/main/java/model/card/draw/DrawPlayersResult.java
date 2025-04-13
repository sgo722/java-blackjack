package model.card.draw;

import model.card.Deck;
import model.participant.Players;

public record DrawPlayersResult(Players players, Deck deck) {
}
