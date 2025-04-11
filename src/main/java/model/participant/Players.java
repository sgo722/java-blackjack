package model.participant;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players create(List<String> playerNames) {
        List<Player> playerList = new ArrayList<>();

        for (String name : playerNames) {
            playerList.add(Player.create(name));
        }

        return new Players(playerList);
    }

    public List<String> getName(){
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
