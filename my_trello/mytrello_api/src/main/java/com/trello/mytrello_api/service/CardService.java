package com.trello.mytrello_api.service;


import com.trello.mytrello_api.dto.CardDto;
import com.trello.mytrello_api.models.Card;

import java.util.List;

public interface CardService {
    List<Card> getBoardCards(int id);

    void addCard(CardDto cardDto);
}
