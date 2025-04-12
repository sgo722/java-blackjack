package model.result;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public static Result from(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) return WIN;
        if (playerScore < dealerScore) return LOSE;
        return DRAW;
    }

    public String toDisplay() {
        return name;
    }
}