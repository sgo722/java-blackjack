package controller;

import model.game.BlackjackGame;
import model.participant.Player;
import view.InputView;
import view.OutputView;

public class BlackjackGameController {

    public void start(){
        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(InputView.inputPlayerNames());

        OutputView.printSplitToPlayers(blackjackGame.getPlayerNames());
        OutputView.printDealerCardList(blackjackGame.getDealerCards());
        OutputView.printPlayersCardList(blackjackGame.getPlayersCards());

        for(String player : blackjackGame.getPlayerNames()){
            while(blackjackGame.carPlayerDraw(player)){
                String op = InputView.printDrawMore(player);
                if(op.equals("y")){
                    blackjackGame.giveCardTo(player);
                    OutputView.printPlayersCardList(blackjackGame.getCardsOf(player));
                }
                if(op.equals("n")){
                    OutputView.printPlayersCardList(blackjackGame.getCardsOf(player));
                    break;
                }
            }
        }

        while(blackjackGame.isDealerDrawingRequired()) {
            OutputView.printReceiveCardToDealer();
            blackjackGame.giveCardToDealer();
        }
        OutputView.printResultOfDealer(blackjackGame.getDealerCards(), blackjackGame.getDealerTotalValue());
        OutputView.printResultOfPlayers(blackjackGame.getPlayersCards(), blackjackGame.getPlayerTotalValue());
    }

}
