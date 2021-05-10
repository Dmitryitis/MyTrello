package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.Team;
import com.trello.mytrello_api.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    @EntityGraph(value = "user-workspace-entity-graph")
    List<Team> findAllByUserEmail(String user);

    Team findById(int id);

}
