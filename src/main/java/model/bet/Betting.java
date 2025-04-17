package model.bet;

public class Betting {

    private final String playerName;
    private final int amount;

    private Betting(String playerName, int amount) {
        this.playerName = playerName;
        this.amount = amount;
    }

    public static Betting from(String playerName, Integer integer) {
        return new Betting(playerName, integer);
    }
}
