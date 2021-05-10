package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.dto.TeamDto;
import com.trello.mytrello_api.models.Team;
import com.trello.mytrello_api.service.impl.TeamServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/v1/team/")
public class TeamControllerV1 {

    @Autowired
    private TeamServiceImpl teamService;

    @ApiOperation(value = "создание рабочего пространства")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "создал рабочее пространтво", response = TeamDto.class)})
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeam(@RequestBody TeamDto teamDto) {
        return ResponseEntity.ok(teamService.createTeam(teamDto));
    }

    @ApiOperation(value = "получение всех пространств")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "все рабочии пространства", response = TeamDto.class)})
    @GetMapping(value = "allteams")
    public ResponseEntity<List<TeamDto>> getTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @ApiOperation(value = "получение всех простанств пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "получение пространств")})
    @GetMapping(value = "teams", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Team>> getTeamsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(teamService.getAllTeamsByEmail(email));
    }
}
