package dto;

import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDto {
    private final String name;
    private final CardsDto cardDtos;

    private ParticipantDto(String name, CardsDto cardDtos) {
        this.name = name;
        this.cardDtos = cardDtos;
    }

    public static ParticipantDto from(Dealer dealer) {
        CardsDto cardsDto = CardsDto.fromCards(dealer.getCards());

        return new ParticipantDto("딜러", cardsDto);
    }

    public static List<ParticipantDto> from(List<Player> players) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        for(Player player: players) {
            CardsDto cardsDto = CardsDto.fromCards(player.getCards());
            participantDtos.add(new ParticipantDto(player.getName(), cardsDto));
        }

        return participantDtos;
    }

    public static List<ParticipantDto> from(Player player) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        CardsDto cardsDto = CardsDto.fromCards(player.getCards());
        participantDtos.add(new ParticipantDto(player.getName(), cardsDto));

        return participantDtos;
    }

    public static List<ParticipantDto> from(Participants participants) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(from(participants.getDealer()));
        participantDtos.addAll(from(participants.getPlayerList()));
        return participantDtos;
    }

    public String getName() {
        return name;
    }

    public CardsDto getCardsDto() {
        return cardDtos;
    }
}
