package controller.command;

import model.game.BlackjackGame;

public class Stop implements DrawCommand {
    public boolean execute(String player, BlackjackGame game) {
        return false;
    }
}
