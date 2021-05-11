package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.BoardColumn;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Integer> {

    @EntityGraph(value = "boardcolumn-board-entity-graph")
    List<BoardColumn> findAllByBoardId(int id);

    BoardColumn findById(long id);
}
