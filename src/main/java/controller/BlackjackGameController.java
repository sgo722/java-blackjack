package controller;

import model.game.BlackjackGame;
import view.InputView;
import view.OutputView;

public class BlackjackGameController {

    public void start(){
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(InputView.inputPlayerNames());

        OutputView.printSplitCard(blackjackGame.getPlayers());
        OutputView.printDealerCardList(blackjackGame.getDealerCards());
        OutputView.printPlayersCardList(blackjackGame.getPlayersCards());
    }

}
