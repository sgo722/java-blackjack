package dto;

import model.card.Card;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDto {
    private final String name;
    private final List<CardDto> cardDtos;

    private ParticipantDto(String name, List<CardDto> cardDtos) {
        this.name = name;
        this.cardDtos = cardDtos;
    }

    public static ParticipantDto from(Dealer dealer) {
        List<CardDto> cardDtos = new ArrayList<>();
        for(Card card : dealer.getCards()){
            cardDtos.add(CardDto.from(card));
        }

        return new ParticipantDto("딜러", cardDtos);
    }

    public static List<ParticipantDto> from(Players players) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        for(Player player: players.getPlayers()) {
            List<CardDto> cardDtos = new ArrayList<>();
            for (Card card : player.getCards()) {
                cardDtos.add(CardDto.from(card));
            }
            participantDtos.add(new ParticipantDto(player.getName(), cardDtos));
        }

        return participantDtos;
    }

    public static List<ParticipantDto> from(Participants participants) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(from(participants.getDealer()));
        participantDtos.addAll(from(participants.getPlayers()));
        return participantDtos;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }
}
