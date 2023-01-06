package ci.kossovo.cardqueryservices.models;

import java.util.List;

import ci.kossovo.ventecoreapi.dtos.cards.CardDto;

public record GetCardQueryResponse(List<CardDto> cardDtos) {}
