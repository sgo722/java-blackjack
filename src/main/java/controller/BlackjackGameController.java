package controller;

import dto.ParticipantDto;
import model.card.Deck;
import model.game.BlackjackGame;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGameController {
    // 블랙잭 게임을 제어한다.
    private BlackjackGame blackjackGame;

    public void start(){
        init();
        draw();
    }

    private void init(){
        Players players = Players.fromNames(InputView.inputPlayerNames());
        blackjackGame = BlackjackGame.create(players, new Deck());
        OutputView.printSplitCard(players.exportPlayerNames());
        OutputView.printInitialDealerCard(ParticipantDto.from(blackjackGame.getDealer()));
        OutputView.printCards(ParticipantDto.from(blackjackGame.getPlayers()));
    }

    private void draw(){
        // 사람 한명잡고 받을거임? 안받으면 다음사람
        for(Player player : blackjackGame.getPlayers()){
            while(blackjackGame.getPlayer(player.getName()).canDraw()) {
                String op = InputView.printDrawMore(player.getName());
                if(op.equals("y")){
                    blackjackGame = blackjackGame.giveCardToPlayer(player.getName());
                    OutputView.printCards(ParticipantDto.from(blackjackGame.getPlayer(player.getName())));
                }
                if(op.equals("n")){
                    break;
                }
            }
        }
    }
}