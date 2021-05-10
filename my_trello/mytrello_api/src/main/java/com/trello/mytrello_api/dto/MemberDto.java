package com.trello.mytrello_api.dto;

import lombok.Data;

@Data
public class MemberDto {
    private int board;
    private long user;

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
