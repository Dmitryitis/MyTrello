package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.dto.ColumnDto;
import com.trello.mytrello_api.models.BoardColumn;
import com.trello.mytrello_api.service.BoardColumnService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/board/")
public class BoardColumnControllerV1 {

    @Autowired
    private BoardColumnService boardColumnService;

    @ApiOperation(value = "получение колонок доски")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @GetMapping(value = "{id}/columns")
    public ResponseEntity<List<BoardColumn>> getBoardColumns(@PathVariable int id) {
        return ResponseEntity.ok(boardColumnService.getBoardColumns(id));
    }

    @ApiOperation(value = "добавление колонки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @PostMapping(value = "add_column")
    public ResponseEntity<?> addBoardColumn(@RequestBody ColumnDto columnDto) {
        boardColumnService.addColumn(columnDto);
        Map<String, Integer> response = new HashMap<>();
        response.put("status", 203);

        return ResponseEntity.ok(response);
    }
}
