package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.BoardEntity;
import com.yellowstone.boardback.repository.resultSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {


    boolean existsByBoardNumber(Integer boardNumber);

    BoardEntity findByBoardNumber (Integer boardNumber);

    @Query(
            value=
            "SELECT " +
            "B.board_number AS boardNumber, " +
            "B.title AS title, " +
            "B.content AS content, " +
            "B.write_datetime AS writerDatetime, " +
            "B.writer_email AS writerEmail, " +
            "U.nickname AS writerNickname, " +
            "U.profile_image AS writerProfileImage " +
            "FROM board AS B " +
            "INNER JOIN user AS U " +
            "ON B.writer_email = U.email " +
            "WHERE board_number = 3 ",
            nativeQuery=true
    )
   GetBoardResultSet getBoard(Integer boardNumber);
}