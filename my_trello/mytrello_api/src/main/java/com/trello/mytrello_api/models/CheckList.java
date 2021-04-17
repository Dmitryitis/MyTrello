package com.trello.mytrello_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "check_list")
public class CheckList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task")
    private String task;

    @Column(name = "status_list", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status;

    @ManyToMany
    @JoinTable
    private List<Card> cards;
}
