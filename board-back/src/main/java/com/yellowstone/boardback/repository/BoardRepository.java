package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

}