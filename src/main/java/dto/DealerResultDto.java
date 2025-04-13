package dto;

import model.result.GameResult;

import java.util.List;

public class DealerResultDto {
    private static final String WIN = "승";
    private static final String LOSE = "패";

    private final int win;
    private final int lose;
    private final int draw;

    public DealerResultDto(int win, int lose, int draw) {
        this.win = win;
        this.lose = lose;
        this.draw = draw;
    }

    public static DealerResultDto from(List<GameResult> gameResults) {
        int win = 0;
        int lose = 0;

        for (GameResult result : gameResults) {
            switch (result.getResult()) {
                case WIN -> lose++;   // 플레이어 승 → 딜러 패
                case LOSE -> win++;   // 플레이어 패 → 딜러 승
            }
        }

        int draw = gameResults.size() - win - lose;
        return new DealerResultDto(win, lose, draw);
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getDraw() {
        return draw;
    }

    public String display() {
        return "딜러: " + win + "승 " + lose + "패 " + draw + "무";
    }
}