package com.yellowstone.boardback.repository;

import com.yellowstone.boardback.entity.FavoriteEntity;
import com.yellowstone.boardback.entity.primaryKey.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavotireRepository extends JpaRepository<FavoriteEntity, FavoritePk> {
}
