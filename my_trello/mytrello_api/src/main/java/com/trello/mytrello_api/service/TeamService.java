package com.trello.mytrello_api.service;

import com.trello.mytrello_api.dto.TeamDto;
import com.trello.mytrello_api.models.Team;

import java.util.List;
import java.util.Map;

public interface TeamService {
    Map<Object, Object> createTeam(TeamDto team);

    List<TeamDto> getAllTeams();

    List<Team> getAllTeamsByEmail(String email);
}
