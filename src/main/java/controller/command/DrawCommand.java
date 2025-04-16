package controller.command;

import model.game.BlackjackGame;

public interface DrawCommand {

    static DrawCommand from(String input) {
        return switch (input.toLowerCase()) {
            case "y" -> new Draw();
            case "n" -> new Stop();
            default -> throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        };
    }


    boolean execute(String player, BlackjackGame game);
}