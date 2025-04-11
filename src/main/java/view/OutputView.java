package view;

import dto.CardDto;
import dto.ParticipantDto;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    // 출력 한다.

    public static void printSplitCard(List<String> players) {
        System.out.print("딜러와 ");
        System.out.print(String.join(", ", players));
        System.out.println("에게 2장을 나누었습니다.");
    }

    public static void printInitialDealerCard(ParticipantDto dealerDto) {
        System.out.print(dealerDto.getName());
        System.out.print("카드: ");

        CardDto cardDto = dealerDto.getCardDtos().get(0);
        String formattedCard = cardDto.getRank() + cardDto.getSuit();

        System.out.println(formattedCard);
    }

    public static void printCards(List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            System.out.print(participantDto.getName());
            System.out.print("카드: ");
            List<CardDto> cardDtos = participantDto.getCardDtos();
            String joined = cardDtos.stream()
                    .map(card -> card.getRank() + card.getSuit())
                    .collect(Collectors.joining(", "));
            System.out.print(joined);

            System.out.println();
        }
    }
}
