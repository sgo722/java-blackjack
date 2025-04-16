package model.participant;

import model.card.Card;

import java.util.*;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players fromNames(List<String> playerNames) {
        List<Player> playerList = new ArrayList<>();

        for (String name : playerNames) {
            playerList.add(Player.fromName(name));
        }

        return new Players(playerList);
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public void receiveInitialCardTo(String playerName, List<Card> cards) {
        Player findPlayer = players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 이름의 플레이어를 찾을 수 없습니다."));

        for(Card card : cards) {
            findPlayer.receive(card);
        }
    }

    public Map<String, List<String>> getNameToCards() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getCards,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Map<String, Integer> getTotalValue() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getTotalValue
                ));
    }

    public Map<String, List<String>> getCardsOf(String playerName) {
        Player player = players.stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어 이름을 찾을 수 없습니다."));

        return Map.of(player.getName(), player.getCards());
    }

    public boolean canDraw(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .map(Player::canDraw)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어 이름을 찾을 수 없습니다."));
    }

    public void giveCardTo(String playerName, Card card) {
        Player findPlayer = players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 이름의 플레이어를 찾을 수 없습니다."));

        findPlayer.receive(card);
    }

    public List<Player> getList() {
        return Collections.unmodifiableList(players);
    }
}
