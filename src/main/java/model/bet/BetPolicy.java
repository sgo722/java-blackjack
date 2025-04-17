package model.bet;

import model.participant.Dealer;
import model.participant.Player;

public interface BetPolicy {

    int calculatePlayerEarning(int betAmount, Player player, Dealer dealer);
}
