package controller;

import controller.command.DrawCommand;
import model.game.BlackjackGame;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackjackGameController {

    public void start(){
        List<String> playerNames = InputView.inputPlayerNames();

        Map<String, Integer> playerNameToBetMoney = InputView.inputBetMoneyFor(playerNames);

        BlackjackGame blackjackGame = BlackjackGame.splitInitialCard(playerNames, playerNameToBetMoney);

        printInitialCards(blackjackGame);

        drawAdditionalCards(blackjackGame);

        printFinalCards(blackjackGame);

        printFinalResults(blackjackGame);
    }

    private static void printInitialCards(BlackjackGame blackjackGame) {
        OutputView.printSplitToPlayers(blackjackGame.getPlayerNames());
        OutputView.printDealerInitialCard(blackjackGame.getDealerCards());
        OutputView.printPlayersCardList(blackjackGame.getPlayerNameToCards());
    }

    private static void drawAdditionalCards(BlackjackGame blackjackGame) {
        drawCardsForAllPlayers(blackjackGame);
        drawUntilDealerMustDraw(blackjackGame);
    }

    private static void drawCardsForAllPlayers(BlackjackGame blackjackGame) {
        for(String player : blackjackGame.getPlayerNames()){
            drawUntilPlayerStop(player, blackjackGame);
        }
    }

    private static void drawUntilDealerMustDraw(BlackjackGame blackjackGame) {
        while(blackjackGame.isDealerDrawingRequired()) {
            OutputView.printReceiveCardToDealer();
            blackjackGame.giveCardToDealer();
        }
    }

    private static void drawUntilPlayerStop(String player, BlackjackGame blackjackGame) {
        boolean drawMore = true;

        while(blackjackGame.canPlayerDraw(player) && drawMore){
            DrawCommand command = DrawCommand.from(InputView.printDrawMore(player));
            drawMore = command.execute(player, blackjackGame);
            OutputView.printPlayersCardList(blackjackGame.getCardsOf(player));
        }
    }

    private static void printFinalCards(BlackjackGame blackjackGame) {
        OutputView.printDealerCardsWithTotalValue(blackjackGame.getDealerCards(), blackjackGame.getDealerTotalValue());
        OutputView.printPlayersCardsWithTotalValue(blackjackGame.getPlayerNameToCards(), blackjackGame.getPlayerTotalValue());
    }

    private static void printFinalResults(BlackjackGame blackjackGame) {
        OutputView.printFinalResult();
        OutputView.printDealerEarningAmount(blackjackGame.getDealerEarningAmount());
        OutputView.printPlayersEarningAmount(blackjackGame.getPlayersEarningAmount());
    }
}
