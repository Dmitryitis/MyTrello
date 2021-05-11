package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.dto.ColumnDto;
import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.models.BoardColumn;
import com.trello.mytrello_api.repository.BoardColumnRepository;
import com.trello.mytrello_api.repository.BoardRepository;
import com.trello.mytrello_api.service.BoardColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BoardColumnServiceImpl implements BoardColumnService {
    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<BoardColumn> getBoardColumns(int id) {
        return boardColumnRepository.findAllByBoardId(id);
    }

    @Override
    public void addColumn(ColumnDto columnDto) {
        BoardColumn boardColumn = new BoardColumn();
        boardColumn.setBoard(boardRepository.findById(columnDto.getBoard()));
        boardColumn.setName(columnDto.getText());
        boardColumn.setDate_created(new Date());
        boardColumn.setDate_updated(new Date());

        boardColumnRepository.save(boardColumn);
    }
}
