package model.participant;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players fromNames(List<String> playerNames) {
        List<Player> playerList = new ArrayList<>();

        for (String name : playerNames) {
            playerList.add(Player.create(name));
        }

        return new Players(playerList);
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public List<String> getName(){
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> exportPlayerNames(){
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Players giveCardTo(String playerName, Card card) {
        List<Player> updatedPlayers = new ArrayList<>();
        for (Player player : players) {
            updatedPlayers.add(player.giveCardIfMatches(playerName, card));
        }
        return new Players(updatedPlayers);
    }
}
