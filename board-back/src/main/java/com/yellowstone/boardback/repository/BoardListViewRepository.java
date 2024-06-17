package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

    List<BoardListViewEntity> findByOrderByWriteDateTimeDesc();
    List<BoardListViewEntity> findTop3ByWriteDateTimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDateTimeDesc(String writeDateTime);
    List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDateTimeDesc(String title, String content);
    List<BoardListViewEntity> findByWriterEmailOrderByWriteDateTimeDesc(String email);
}
