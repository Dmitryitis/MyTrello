package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.dto.MemberDto;
import com.trello.mytrello_api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @ApiOperation(value = "Удаления участника")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @DeleteMapping(value = "member/{id}")
    public ResponseEntity<?> deleteBoardMember(@PathVariable long id) {

        boardService.deleteMember(id);
        System.out.println("удален");

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 204);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Добавить участника")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "добавил", response = MemberDto.class)})
    @PostMapping(value = "add_member")
    public ResponseEntity<?> addMember(@RequestBody MemberDto member) {
        boardService.addMember(member);
        Map<String, Integer> response = new HashMap<>();
        response.put("status", 203);
        return ResponseEntity.ok(response);
    }
}
