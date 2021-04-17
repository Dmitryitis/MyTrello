package com.trello.mytrello_api.dto;

import com.trello.mytrello_api.models.Team;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamDto {
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TeamDto from(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
        return teamDto;
    }

    public static List<TeamDto> from(List<Team> teams) {
        return teams.stream().map(TeamDto::from).collect(Collectors.toList());
    }
}
