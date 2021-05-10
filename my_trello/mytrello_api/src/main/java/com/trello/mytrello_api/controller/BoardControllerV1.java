package com.trello.mytrello_api.controller;


import com.trello.mytrello_api.aop.LoggingAspect;
import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.service.BoardService;
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
import java.util.logging.Logger;

@RestController
@Slf4j
@RequestMapping("/api/v1/board/")
public class BoardControllerV1 {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Autowired
    private BoardService boardService;

    @ApiOperation(value = "создание доски")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "доска создана")})
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBoard(@RequestBody Map<Object, Object> json) {
        return ResponseEntity.ok(boardService.createBoard(json));
    }

    @ApiOperation(value = "получение всех досок пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "получение досок")})
    @GetMapping(value = "boards")
    public ResponseEntity<List<Board>> getBoards(@RequestParam String email) {
        System.out.println("Board: " + email);
        return ResponseEntity.ok(boardService.getAllBoardByEmail(email));
    }


    @ApiOperation(value = "Доска")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Доска")})
    @GetMapping(value = "{id}/board")
    public ResponseEntity<?> getBoard(@PathVariable int id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }
}
