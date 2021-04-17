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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_column")
    private BoardColumn boardColumn;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
}
