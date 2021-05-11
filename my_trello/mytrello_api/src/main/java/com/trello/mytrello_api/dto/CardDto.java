package com.trello.mytrello_api.dto;

import lombok.Data;

@Data
public class CardDto {
    private String title;
    private int board;
    private String author;
    private long board_column;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getBoard_column() {
        return board_column;
    }

    public void setBoard_column(long board_column) {
        this.board_column = board_column;
    }
}
