package model.result;

import model.participant.Dealer;
import model.participant.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResults {

    private final List<GameResult> gameResults;

    private GameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults of(Players players, Dealer dealer){
        List<GameResult> gameResults = players.getList().stream()
                .map(player -> GameResult.of(player, dealer))
                .toList();
        return new GameResults(gameResults);
    }


    public Map<String, String> getPlayerResultsDisplay() {
        return gameResults.stream()
                .collect(Collectors.toMap(
                        GameResult::getPlayerName,
                        GameResult::getResult,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public List<Integer> getDealerResultSummary() {
        int win = countDealerWins();
        int draw = countDraws();
        int lose = countDealerLosses();
        return List.of(win, draw, lose);
    }

    private int countDraws() {
        return gameResults.size() - countDealerWins() - countDealerLosses();
    }

    private int countDealerWins() {
        return (int) gameResults.stream()
                .filter(GameResult::isDealerWin)
                .count();
    }

    private int countDealerLosses() {
        return (int) gameResults.stream()
                .filter(GameResult::isDealerLose)
                .count();
    }
}
