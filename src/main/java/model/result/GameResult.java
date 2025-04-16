package model.result;

import model.participant.Dealer;
import model.participant.Player;

public class GameResult {
    private final String playerName;
    private final Result result;

    private GameResult(String playerName, Result result) {
        this.playerName = playerName;
        this.result = result;
    }

    public static GameResult of(Player player, Dealer dealer){
        if(player.isBust()) return new GameResult(player.getName(), Result.LOSE);
        if(dealer.isBust()) return new GameResult(player.getName(), Result.WIN);
        return new GameResult(player.getName(), Result.from(player.getTotalValue(), dealer.getTotalValue()));
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getResult() {
        return result.toDisplay();
    }

    public boolean isDealerLose() {
        return result == Result.WIN;
    }

    public boolean isDealerWin() {
        return result == Result.LOSE;
    }
}
