package controller;

import dto.ParticipantDto;
import model.card.Deck;
import model.game.BlackjackGame;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGameController {
    // 블랙잭 게임을 제어한다.
    public void start(){
        init();
    }

    private void init(){
        Players players = Players.fromNames(InputView.inputPlayerNames());
        BlackjackGame blackjackGame = BlackjackGame.create(players, new Deck());
        OutputView.printSplitCard(players.exportPlayerNames());
        OutputView.printInitialDealerCard(ParticipantDto.from(blackjackGame.getDealer()));
        OutputView.printCards(ParticipantDto.from(blackjackGame.getPlayers()));

    }
}