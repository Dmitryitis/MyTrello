package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.Team;
import com.trello.mytrello_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByUser(User user);

    Team findById(int id);
}
