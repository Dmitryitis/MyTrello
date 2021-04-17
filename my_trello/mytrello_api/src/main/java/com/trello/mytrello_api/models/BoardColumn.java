package com.trello.mytrello_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boardcolumn")
public class BoardColumn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "column_name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date date_created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated")
    private Date date_updated;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
