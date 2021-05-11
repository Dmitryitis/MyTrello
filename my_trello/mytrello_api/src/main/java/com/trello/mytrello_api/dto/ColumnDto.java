package com.trello.mytrello_api.dto;

import lombok.Data;

@Data
public class ColumnDto {
    private String text;
    private int board;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }
}
