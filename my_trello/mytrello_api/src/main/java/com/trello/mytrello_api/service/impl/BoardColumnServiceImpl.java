package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.models.BoardColumn;
import com.trello.mytrello_api.repository.BoardColumnRepository;
import com.trello.mytrello_api.repository.BoardRepository;
import com.trello.mytrello_api.service.BoardColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardColumnServiceImpl implements BoardColumnService {
    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Override
    public List<BoardColumn> getBoardColumns(int id) {
        return boardColumnRepository.findAllByBoardId(id);
    }
}
