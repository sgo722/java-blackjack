package dto;

import model.result.GameResult;

import java.util.List;

public class PlayerResultDto {
    private final String playerName;
    private final String result; // "승", "패", "무"

    public PlayerResultDto(String playerName, String result) {
        this.playerName = playerName;
        this.result = result;
    }

    public static PlayerResultDto from(GameResult gameResult) {
        return new PlayerResultDto(
                gameResult.getPlayerName(),
                gameResult.getResult()
        );
    }

    public static List<PlayerResultDto> from(List<GameResult> results) {
        return results.stream()
                .map(PlayerResultDto::from)
                .toList();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getResult() {
        return result;
    }
}