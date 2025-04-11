package controller;

import model.participant.Players;
import view.InputView;

public class BlackjackGameController {
    // 블랙잭 게임을 제어한다.

    public BlackjackGameController() {
    }

    public void start(){
        init();
    }

    private void init(){
        Players players = Players.makePlayer(InputView.inputPlayerNames());
    }
}
