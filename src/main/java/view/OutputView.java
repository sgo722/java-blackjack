package view;

import java.util.List;

public class OutputView {
    // 출력 한다.

    public static void printSplitCard(List<String> players){
        System.out.print("딜러와 ");
        System.out.print(String.join(", ", players));
        System.out.println("에게 2장을 나누었습니다.");
    }
}
