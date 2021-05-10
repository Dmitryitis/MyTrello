package com.trello.mytrello_api.service;

import com.trello.mytrello_api.models.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    Map<Object, Object> createBoard(Map<Object, Object> json);


    List<Board> getAllBoardByEmail(String email);
}
