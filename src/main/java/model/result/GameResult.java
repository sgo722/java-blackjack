package model.result;

import model.participant.Dealer;
import model.participant.Player;

public class GameResult {
    private final String playerName;
    private final Result result;

    public GameResult(String playerName, Result result) {
        this.playerName = playerName;
        this.result = result;
    }

    public static GameResult of(Player player, Dealer dealer) {
        if (player.isBust()) {
            return new GameResult(player.getName(), Result.LOSE);
        }

        if (dealer.isBust()) {
            return new GameResult(player.getName(), Result.WIN);
        }

        return new GameResult(player.getName(), Result.from(player.getTotalValue(), dealer.getTotalValue()));
    }

    public String getResult() {
        return result.toDisplay();
    }

    public String getPlayerName() {
        return playerName;
    }
}
