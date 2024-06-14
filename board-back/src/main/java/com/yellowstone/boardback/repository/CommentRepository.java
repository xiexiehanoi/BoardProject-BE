package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}