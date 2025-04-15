package view;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printSplitToPlayers(List<String> playerNames) {
        System.out.print("딜러와 ");
        System.out.print(String.join(", ", playerNames));
        System.out.println("에게 2장을 나누었습니다.");
    }

    public static void printDealerCardList(List<String> dealerCards) {
        System.out.print("딜러카드: ");

        String cardList = String.join(", ", dealerCards);
        System.out.println(cardList);
    }

    public static void printPlayersCardList(Map<String, List<String>> playersCards) {

        for(Map.Entry<String, List<String>> entry : playersCards.entrySet()) {
            String playerName = entry.getKey();
            List<String> playerCardList = entry.getValue();
            String cardList = String.join(", ", playerCardList);

            System.out.print(playerName);
            System.out.print("카드: ");
            System.out.print(cardList);
            System.out.println();
        }

    }

    public static void printReceiveCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printResultOfDealer(List<String> dealerCards, int totalValue) {
        System.out.print("딜러카드: ");

        String cardList = String.join(", ", dealerCards);
        System.out.print(cardList);

        System.out.print(" - ");
        System.out.print("결과 : ");
        System.out.println(totalValue);
    }

    public static void printResultOfPlayers(Map<String, List<String>> playersCards, Map<String, Integer> playerNameTotalValue) {
        for(Map.Entry<String, List<String>> entry : playersCards.entrySet()) {
            String playerName = entry.getKey();
            int totalValue = playerNameTotalValue.get(playerName);
            List<String> playerCardList = entry.getValue();
            String cardList = String.join(", ", playerCardList);

            System.out.print(playerName);
            System.out.print("카드: ");
            System.out.print(cardList);
            System.out.print(" - ");
            System.out.print("결과 : ");
            System.out.println(totalValue);
        }
    }
}
