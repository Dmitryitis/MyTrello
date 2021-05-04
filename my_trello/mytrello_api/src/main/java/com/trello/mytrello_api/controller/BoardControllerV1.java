package com.trello.mytrello_api.controller;


import com.trello.mytrello_api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/board/")
public class BoardControllerV1 {

    @Autowired
    private BoardService boardService;

    @PostMapping(value = "create")
    public ResponseEntity<?> createBoard(@RequestBody Map<Object, Object> json) {

        return ResponseEntity.ok(boardService.createBoard(json));
    }
}
