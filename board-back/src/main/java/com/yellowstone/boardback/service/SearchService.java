package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.response.board.GetPopularListResponseDto;
import com.yellowstone.boardback.dto.response.board.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);

}
