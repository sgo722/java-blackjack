package controller;

import model.card.Deck;
import model.game.BlackjackGame;
import model.participant.Dealer;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGameController {
    // 블랙잭 게임을 제어한다.
    private Deck deck;

    public BlackjackGameController() {
    }

    public void start(){
        init();
    }

    private void init(){
        Players players = Players.create(InputView.inputPlayerNames());
        deck = new Deck();
        BlackjackGame blackjackGame = BlackjackGame.create(players, deck);
        OutputView.printSplitCard(players.getName());
    }
}