package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.SearchLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchLogRepository extends JpaRepository<SearchLogEntity,Integer> {
}
