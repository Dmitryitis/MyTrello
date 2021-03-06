package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.dto.MemberDto;
import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.models.BoardMember;
import com.trello.mytrello_api.models.User;
import com.trello.mytrello_api.repository.BoardMemberRepository;
import com.trello.mytrello_api.repository.BoardRepository;
import com.trello.mytrello_api.repository.TeamRepository;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private BoardMemberRepository boardMemberRepository;

    @Override
    public Map<Object, Object> createBoard(Map<Object, Object> json) {
        Board boardDto = new Board();
        System.out.println(json.get("team_id"));
        boardDto.setName((String) json.get("nameBoard"));
        boardDto.setUser(userRepository.findByEmail((String) json.get("email")));
        boardDto.setTeam(teamRepository.findById((int) json.get("team_id")));
        boardDto.setDate_created(new Date());

        boardRepository.save(boardDto);

        Map<Object, Object> response = new HashMap<>();
        response.put("status", 200);
        return response;
    }

    @Override
    public List<Board> getAllBoardByEmail(String email) {
        return boardRepository.findAllByUserEmail(email);
    }

    @Override
    public List<BoardMember> getBoardMembers(int id) {
        return boardMemberRepository.findAllByBoardId(id);
    }

    @Override
    public Board getBoard(int id) {
        return boardRepository.findById(id);
    }

    @Override
    public void deleteMember(long id) {
        boardMemberRepository.deleteBoardMemberByUserId(id);
    }

    @Override
    public void addMember(MemberDto member) {
        BoardMember boardMember = new BoardMember();
        boardMember.setBoard(boardRepository.findById(member.getBoard()));
        boardMember.setUser(userRepository.findById(member.getUser()));

        boardMemberRepository.save(boardMember);
    }
}
