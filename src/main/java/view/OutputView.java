package view;

import dto.CardDto;
import dto.CardsDto;
import dto.ParticipantDto;

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

        CardsDto cardsDto = dealerDto.getCardsDto();
        CardDto cardDto = cardsDto.getCardDtoList().get(0);
        String formattedCard = cardDto.getRank() + cardDto.getSuit();

        System.out.println(formattedCard);
    }

    public static void printCards(List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            System.out.print(participantDto.getName());
            System.out.print("카드: ");

            CardsDto cardsDto = participantDto.getCardsDto();
            List<CardDto> cardDtos = cardsDto.getCardDtoList();
            String joined = cardDtos.stream()
                    .map(card -> card.getRank() + card.getSuit())
                    .collect(Collectors.joining(", "));
            System.out.print(joined);

            System.out.println();
        }
    }

    public static void printReceiveCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printResult(List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            System.out.print(participantDto.getName());
            System.out.print("카드: ");

            CardsDto cardsDto = participantDto.getCardsDto();
            List<CardDto> cardDtos = cardsDto.getCardDtoList();
            String joined = cardDtos.stream()
                    .map(card -> card.getRank() + card.getSuit())
                    .collect(Collectors.joining(", "));
            System.out.print(joined);
            System.out.print(" - 결과: ");
            System.out.print(cardsDto.getTotalValue());

            System.out.println();
        }
    }
}
