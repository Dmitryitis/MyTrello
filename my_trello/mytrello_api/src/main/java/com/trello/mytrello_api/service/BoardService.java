package com.trello.mytrello_api.service;

import com.trello.mytrello_api.dto.MemberDto;
import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.models.BoardMember;
import com.trello.mytrello_api.models.User;

import java.util.List;
import java.util.Map;

public interface BoardService {
    Map<Object, Object> createBoard(Map<Object, Object> json);

    List<Board> getAllBoardByEmail(String email);

    List<BoardMember> getBoardMembers(int id);

    Board getBoard(int id);

    void deleteMember(long id);

    void addMember(MemberDto member);
}
