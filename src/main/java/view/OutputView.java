package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printSplitCard(List<String> playerNames) {
        System.out.print("딜러와 ");
        System.out.print(String.join(", ", playerNames));
        System.out.println("에게 2장을 나누었습니다.");
    }

    public static void printDealerCardList(List<String> dealerCardList) {
        System.out.print("딜러카드: ");

        String cardList = String.join(", ", dealerCardList);
        System.out.println(cardList);
    }

    public static void printPlayersCardList(Map<String, List<String>> playersCardList) {

        for(Map.Entry<String, List<String>> entry : playersCardList.entrySet()) {
            String playerName = entry.getKey();
            List<String> playerCardList = entry.getValue();
            String cardList = String.join(", ", playerCardList);

            System.out.print(playerName);
            System.out.print("카드: ");
            System.out.print(cardList);
            System.out.println();
        }

    }
}
