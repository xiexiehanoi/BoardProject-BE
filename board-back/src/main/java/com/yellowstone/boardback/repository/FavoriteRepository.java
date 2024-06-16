package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.FavoriteEntity;
import com.yellowstone.boardback.entity.primaryKey.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber,String userEmail);

}
