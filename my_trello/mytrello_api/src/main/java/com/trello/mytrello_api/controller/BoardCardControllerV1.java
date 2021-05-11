package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.dto.CardDto;
import com.trello.mytrello_api.service.CardService;
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
public class BoardCardControllerV1 {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "карточки доски")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "карточки")})
    @GetMapping(value = "{id}/cards")
    public ResponseEntity<?> getBoards(@PathVariable int id) {
        return ResponseEntity.ok(cardService.getBoardCards(id));
    }

    @ApiOperation(value = "добавление карточки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @PostMapping(value = "add_card")
    public ResponseEntity<?> addCard(@RequestBody CardDto cardDto) {

        cardService.addCard(cardDto);

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 203);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "обновление статуса карточки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @PutMapping(value = "{column}/update_card/{id}")
    public ResponseEntity<?> updateCard(@PathVariable int column, @PathVariable long id) {

        cardService.updateCard(column, id);

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 200);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "архивирование карточки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @PutMapping(value = "archive_card/{id}")
    public ResponseEntity<?> archiveCard(@PathVariable int id) {

        cardService.archiveCard(id);

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 200);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "возвращение карточки на доску")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @PutMapping(value = "back_card/{id}")
    public ResponseEntity<?> backCard(@PathVariable int id) {

        cardService.backCard(id);

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 200);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "удаление карточки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @DeleteMapping(value = "delete_card/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable int id) {
        cardService.deleteCard(id);

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 203);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "получение карточки по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @GetMapping(value = "card/{id}")
    public ResponseEntity<?> getCard(@PathVariable long id) {
        return ResponseEntity.ok(cardService.getCard(id));
    }

    @ApiOperation(value = "обновление описания карточки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "успешно")})
    @PutMapping(value = "description_card/{id}")
    public ResponseEntity<?> descriptionCard(@PathVariable long id, @RequestBody String text) {

        cardService.descriptionCard(id, text);

        Map<String, Integer> response = new HashMap<>();
        response.put("status", 203);

        return ResponseEntity.ok(response);
    }

}
