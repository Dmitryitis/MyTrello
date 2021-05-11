package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.models.Card;
import com.trello.mytrello_api.repository.BoardCardRepository;
import com.trello.mytrello_api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private BoardCardRepository boardCardRepository;

    @Override
    public List<Card> getBoardCards(int id) {
        return boardCardRepository.findAllByBoardId(id);
    }
}
