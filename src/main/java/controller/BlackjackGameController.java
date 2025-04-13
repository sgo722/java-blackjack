package controller;

import dto.DealerResultDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import model.card.Deck;
import model.game.BlackjackGame;
import model.participant.Player;
import model.participant.Players;
import model.result.GameResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackGameController {
    // 블랙잭 게임을 제어한다.
    private BlackjackGame blackjackGame;

    public void start(){
        init();
        draw();
        result();
    }

    private void init(){
        Players players = Players.fromNames(InputView.inputPlayerNames());
        blackjackGame = BlackjackGame.create(players, new Deck());
        OutputView.printSplitCard(players.exportPlayerNames());
        OutputView.printInitialDealerCard(ParticipantDto.from(blackjackGame.getDealer()));
        OutputView.printCards(ParticipantDto.from(blackjackGame.getPlayers()));
    }

    private void draw(){
        for(Player player : blackjackGame.getPlayers()){
            while(blackjackGame.canDraw(player.getName())) {
                String op = InputView.printDrawMore(player.getName());
                if(op.equals("y")){
                    blackjackGame = blackjackGame.giveCardToPlayer(player.getName());
                    OutputView.printCards(ParticipantDto.from(blackjackGame.findPlayer(player.getName())));
                }
                if(op.equals("n")){
                    break;
                }
            }
        }
        while(blackjackGame.getDealer().canDraw()) {
            OutputView.printReceiveCardToDealer();
            blackjackGame = blackjackGame.giveCardToDealer();
        }

        OutputView.printResult(ParticipantDto.from(blackjackGame.getParticipants()));
    }

    private void result(){
        List<GameResult> results = blackjackGame.getResult();
        DealerResultDto dealerResultDto = DealerResultDto.from(results);
        List<PlayerResultDto> playerResultDtos = PlayerResultDto.from(results);

        OutputView.printFinalResults(dealerResultDto, playerResultDtos);
    }
}