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
@NamedEntityGraph(
        name = "boardcolumn-board-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "board", subgraph = "board-details")
        },
        subgraphs = {
                @NamedSubgraph(name = "board-details",
                        attributeNodes = {
                                @NamedAttributeNode(value = "user"),
                                @NamedAttributeNode(value = "team")
                        })
        }
)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
