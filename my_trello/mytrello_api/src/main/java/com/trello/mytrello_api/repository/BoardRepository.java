package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.Board;
import com.trello.mytrello_api.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @EntityGraph(value = "board-team-user-entity-graph")
    List<Board> findAllByUserEmail(String email);
    List<Board> findAllByUser(User user);
}
