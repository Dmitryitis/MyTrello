package com.trello.mytrello_api.controller;


import com.trello.mytrello_api.aop.LoggingAspect;
import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Slf4j
@RequestMapping("/api/v1/board/")
public class BoardControllerV1 {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Autowired
    private BoardService boardService;

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
}
