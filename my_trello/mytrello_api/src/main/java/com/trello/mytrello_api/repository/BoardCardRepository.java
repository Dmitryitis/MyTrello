package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.Card;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCardRepository extends JpaRepository<Card, Long> {

    @EntityGraph(value = "card-entity-graph")
    List<Card> findAllByBoardId(int id);
}
