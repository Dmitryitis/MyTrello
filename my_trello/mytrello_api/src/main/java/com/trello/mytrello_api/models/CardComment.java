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
@Table(name = "card_comment")
public class CardComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "board_member_id")
    private BoardMember boardMember;

    @ManyToOne
    @JoinColumn(name = "board_card")
    private Card card;

    @Column(name = "comment")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date date_created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated")
    private Date date_updated;
}
