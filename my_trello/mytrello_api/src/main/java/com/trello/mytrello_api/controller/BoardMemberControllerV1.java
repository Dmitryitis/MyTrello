package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board/")
public class BoardMemberControllerV1 {

    @Autowired
    private BoardService boardService;

    @ApiOperation(value = "Участники доски")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "участники доски")})
    @GetMapping(value = "{id}/members")
    public ResponseEntity<?> getBoardMembers(@PathVariable int id) {
        return ResponseEntity.ok(boardService.getBoardMembers(id));
    }
}
