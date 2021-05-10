package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.models.BoardColumn;
import com.trello.mytrello_api.service.BoardColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board/")
public class BoardColumnControllerV1 {

    @Autowired
    private BoardColumnService boardColumnService;

    @GetMapping(value = "{id}/columns")
    public ResponseEntity<List<BoardColumn>> getBoardColumns(@PathVariable int id) {
        return ResponseEntity.ok(boardColumnService.getBoardColumns(id));
    }
}
