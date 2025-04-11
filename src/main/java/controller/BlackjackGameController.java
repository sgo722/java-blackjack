package controller;

import model.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackGameController {
    // 블랙잭 게임을 제어한다.

    public BlackjackGameController() {
    }

    public void start(){
        init();
    }

    private void init(){
        Players players = Players.create(InputView.inputPlayerNames());
        OutputView.printSplitCard(players.getName());

    }
}
