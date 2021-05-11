package com.trello.mytrello_api.service;

import com.trello.mytrello_api.dto.ColumnDto;
import com.trello.mytrello_api.models.BoardColumn;

import java.util.List;

public interface BoardColumnService {
    List<BoardColumn> getBoardColumns(int id);

    void addColumn(ColumnDto columnDto);
}
