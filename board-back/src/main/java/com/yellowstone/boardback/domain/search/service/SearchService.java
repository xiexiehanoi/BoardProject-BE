package com.yellowstone.boardback.domain.search.service;

import com.yellowstone.boardback.domain.search.dto.response.GetPopularListResponseDto;
import com.yellowstone.boardback.domain.search.dto.response.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);

}
