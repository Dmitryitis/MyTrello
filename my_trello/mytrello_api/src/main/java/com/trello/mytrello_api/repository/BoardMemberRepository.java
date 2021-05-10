package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.BoardMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardMemberRepository extends JpaRepository<BoardMember, Integer> {
    @EntityGraph(value = "board-member-entity-graph")
    List<BoardMember> findBoardMemberByBoardId(int id);
}
