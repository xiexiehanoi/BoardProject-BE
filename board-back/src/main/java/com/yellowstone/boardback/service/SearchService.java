package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.response.board.GetPopularListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();

}
