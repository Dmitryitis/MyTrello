package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.dto.CardDto;
import com.trello.mytrello_api.models.Card;
import com.trello.mytrello_api.repository.BoardCardRepository;
import com.trello.mytrello_api.repository.BoardColumnRepository;
import com.trello.mytrello_api.repository.BoardRepository;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private BoardCardRepository boardCardRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Card> getBoardCards(int id) {
        return boardCardRepository.findAllByBoardId(id);
    }

    @Override
    public void addCard(CardDto cardDto) {
        Card card = new Card();
        card.setTitle(cardDto.getTitle());
        card.setBoard(boardRepository.findById(cardDto.getBoard()));
        card.setBoardColumn(boardColumnRepository.findById(cardDto.getBoard_column()));
        card.setUser(userRepository.findByEmail(cardDto.getAuthor()));

        boardCardRepository.save(card);
    }

    @Override
    public void updateCard(int column, long card) {
        Card update_card = boardCardRepository.findById(card);
        update_card.setBoardColumn(boardColumnRepository.findById(column));

        boardCardRepository.save(update_card);
    }

    @Override
    public void archiveCard(int id) {
        Card archive_card = boardCardRepository.findById(id);
        archive_card.setArchive(true);
        boardCardRepository.save(archive_card);
    }

    @Override
    public void backCard(int id) {
        Card back_card = boardCardRepository.findById(id);
        back_card.setArchive(false);

        boardCardRepository.save(back_card);
    }

    @Override
    public void deleteCard(int id) {
        Card delete_card = boardCardRepository.findById(id);
        boardCardRepository.delete(delete_card);
    }
}
