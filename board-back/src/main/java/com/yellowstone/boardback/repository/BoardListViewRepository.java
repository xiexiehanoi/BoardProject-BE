package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {
    
}
