package com.yellowstone.boardback.domain.board.repository.resultset;

public interface GetBoardResultSet {

    Integer getBoardNumber();
    String getTitle();
    String getContent();
    String getWriteDateTime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();

}
