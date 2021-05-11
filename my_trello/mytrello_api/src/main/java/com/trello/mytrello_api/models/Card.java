package com.trello.mytrello_api.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "card-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "user"),
                @NamedAttributeNode(value = "boardColumn", subgraph = "boardcolumn-details"),
                @NamedAttributeNode(value = "board", subgraph = "board-details")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "boardcolumn-details",
                        attributeNodes = {
                                @NamedAttributeNode(value = "board")
                        }
                ),
                @NamedSubgraph(name = "board-details",
                        attributeNodes = {
                                @NamedAttributeNode(value = "user"),
                                @NamedAttributeNode(value = "team")
                        })
        }
)
@Table(name = "board_card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "status_archive", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean archive;

    public boolean isArchive() {
        return archive;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_column")
    private BoardColumn boardColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board")
    private Board board;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable
    private List<BoardMember> boardMembers;

    @ManyToMany
    @JoinTable
    private List<AttachFile> files;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, name = "deadline")
    private Date deadline;

    @ManyToMany
    @JoinTable
    private List<CheckList> checkLists;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BoardColumn getBoardColumn() {
        return boardColumn;
    }

    public void setBoardColumn(BoardColumn boardColumn) {
        this.boardColumn = boardColumn;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<BoardMember> getBoardMembers() {
        return boardMembers;
    }

    public void setBoardMembers(List<BoardMember> boardMembers) {
        this.boardMembers = boardMembers;
    }

    public List<AttachFile> getFiles() {
        return files;
    }

    public void setFiles(List<AttachFile> files) {
        this.files = files;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }
}
