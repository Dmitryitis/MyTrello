package com.trello.mytrello_api.service.impl;


import com.trello.mytrello_api.dto.TeamDto;
import com.trello.mytrello_api.models.Team;
import com.trello.mytrello_api.repository.TeamRepository;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.trello.mytrello_api.dto.TeamDto.from;

@Component
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<Object, Object> createTeam(TeamDto teamDto) {
        Team team = new Team();
        Date date = new Date();
        team.setName(teamDto.getName());
        team.setUser(userRepository.findByEmail(teamDto.getEmail()));
        team.setDate_created(date);
        teamRepository.save(team);
        Map<Object, Object> response = new HashMap<>();
        response.put("status", 200);
        return response;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return from(teamRepository.findAll());
    }


    @Override
    public List<Team> getAllTeamsByEmail(String email) {
        return teamRepository.findAllByUserEmail(email);
    }
}
