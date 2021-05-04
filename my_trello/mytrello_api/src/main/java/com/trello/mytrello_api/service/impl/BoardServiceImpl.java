package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.repository.BoardRepository;
import com.trello.mytrello_api.repository.TeamRepository;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Map<Object, Object> createBoard(Map<Object, Object> json) {
        Board boardDto = new Board();
        boardDto.setName((String) json.get("email"));
        boardDto.setTeam(teamRepository.findById((int) json.get("team_id")));
        boardDto.setUser(userRepository.findByEmail((String) json.get("email")));
        boardDto.setDate_created(new Date());

        boardRepository.save(boardDto);

        Map<Object, Object> response = new HashMap<>();
        response.put("status", 200);
        return response;
    }
}
