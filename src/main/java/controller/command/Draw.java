package controller.command;

import model.game.BlackjackGame;

public class Draw implements DrawCommand {
    public boolean execute(String player, BlackjackGame game) {
        game.giveCardTo(player);
        return true;
    }
}
