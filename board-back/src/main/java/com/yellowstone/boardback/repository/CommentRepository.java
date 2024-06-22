package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.CommentEntity;
import com.yellowstone.boardback.repository.resultSet.GetCommentListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query(
        value =
        "SELECT "+
            "U.nickname AS nickname, "+
            "U.profile_image AS profile_image, "+
            "C.write_datetime AS writeDatetime, "+
            "C.comment AS comment "+
        "FROM comment AS C "+
        "INNER JOIN user AS U "+
        "ON C.user_email = U.email "+
        "WHERE C.board_number = ?1 "+
        "ORDER BY writeDatetime DESC",
        nativeQuery = true
    )
    List<GetCommentListResultSet> getCommentList(Integer boardNumber);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}