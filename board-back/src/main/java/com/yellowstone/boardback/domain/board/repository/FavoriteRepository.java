package com.yellowstone.boardback.domain.board.repository;

import com.yellowstone.boardback.domain.board.entity.FavoriteEntity;
import com.yellowstone.boardback.domain.board.entity.primaryKey.FavoritePk;
import com.yellowstone.boardback.domain.board.repository.resultset.GetFavoriteListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber,String userEmail);

    @Query(
        value =
        "SELECT "+
        "    U.email AS email, "+
        "    U.nickname AS nickname, "+
        "    U.profile_image AS profileImage "+
        "FROM favorite AS F "+
        "INNER JOIN user AS U "+
        "ON F.user_email = U.email "+
        "WHERE F.board_number = ?1",
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}
