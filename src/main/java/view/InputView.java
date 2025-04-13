package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    // 입력 받는다.
    private static final String NAME_DELIMITER = ",";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = SCANNER.nextLine();

        return List.of(playerNames.split(NAME_DELIMITER));
    }

    public static String printDrawMore(String name) {
        System.out.print(name);
        System.out.println(" 한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n)");

        return SCANNER.nextLine();
    }
}
