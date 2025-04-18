package model.bet;

import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetManager {

    private final List<Bet> bets;
    private final BetPolicy betPolicy;

    private BetManager(List<Bet> bets) {
        this.bets = bets;
        this.betPolicy = new DefaultBetPolicy();
    }

    public static BetManager from(Map<String, Integer> playerNameToBetMoney) {
        List<Bet> bets = new ArrayList<>();
        for(String playerName : playerNameToBetMoney.keySet()) {
            bets.add(Bet.from(playerName, playerNameToBetMoney.get(playerName)));
        }

        return new BetManager(bets);
    }

    public Integer calculateDealerEarning(Players players, Dealer dealer) {
        return bets.stream()
                .mapToInt(bet -> {
                    Player player = players.findPlayerBy(bet.getPlayerName());
                    int playerEarningAmount = betPolicy.calculatePlayerEarning(bet.getAmount(), player, dealer);
                    return calculateDealerEarningBy(playerEarningAmount);
                })
                .sum();
    }

    private int calculateDealerEarningBy(int playerEarningAmount) {
        return -playerEarningAmount;
    }

    public Map<String, Integer> getPlayerNameToEarningAmount(Players players, Dealer dealer) {
        Map<String, Integer> playerNameToEarningAmount = new LinkedHashMap<>();

        for(Bet bet : bets) {
            Player player = players.findPlayerBy(bet.getPlayerName());
            int amount = bet.getAmount();
            int earningAmount = betPolicy.calculatePlayerEarning(amount, player, dealer);
            playerNameToEarningAmount.put(player.getName(), earningAmount);
        }

        return playerNameToEarningAmount;
    }
}

