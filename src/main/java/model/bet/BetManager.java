package model.bet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BetManager {

    private final List<Betting> bettings;

    private BetManager(List<Betting> bettings) {
        this.bettings = bettings;
    }

    public static BetManager from(Map<String, Integer> playerNameToBetMoney) {
        List<Betting> bettings = new ArrayList<>();
        for(String playerName : playerNameToBetMoney.keySet()) {
            bettings.add(Betting.from(playerName, playerNameToBetMoney.get(playerName)));
        }

        return new BetManager(bettings);
    }

    public BetResult
}

