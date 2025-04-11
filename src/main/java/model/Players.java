package model;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }


    public static Players makePlayer(List<String> playerNames) {
        List<Player> playerList = new ArrayList<>();
        for (String name : playerNames) {
            playerList.add(Player.makePlayer(name));
        }

        return new Players(playerList);
    }
}
