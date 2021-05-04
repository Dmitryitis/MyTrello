package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
