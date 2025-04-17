package model.bet;

import model.participant.Dealer;
import model.participant.Player;

public class DefaultBetPolicy implements BetPolicy {
    static final double INITIAL_BLACKJACK_BONUS = 1.5;
    static final int LOSS_MULTIPLE = -1;

    @Override
    public int calculatePlayerEarning(int betAmount, Player player, Dealer dealer) {
        if(player.isInitialBlackjack()) return (int)(betAmount * INITIAL_BLACKJACK_BONUS);
        if(player.isBlackjack() && dealer.isBlackjack()) return betAmount;
        if(player.isBust()) return betAmount * LOSS_MULTIPLE;
        if(dealer.isBust()) return betAmount;
        if(player.getTotalValue() > dealer.getTotalValue()) return betAmount;
        if(player.getTotalValue() == dealer.getTotalValue()) return betAmount;
        return betAmount * LOSS_MULTIPLE;
    }
}
