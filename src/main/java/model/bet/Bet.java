package model.bet;

public class Bet {

    private final String playerName;
    private final int amount;

    private Bet(String playerName, int amount) {
        this.playerName = playerName;
        this.amount = amount;
    }

    public static Bet from(String playerName, Integer integer) {
        return new Bet(playerName, integer);
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAmount() {
        return amount;
    }
}
