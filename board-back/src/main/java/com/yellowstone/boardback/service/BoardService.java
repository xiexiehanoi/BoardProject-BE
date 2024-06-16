package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.request.board.PostBoardRequestDto;
import com.yellowstone.boardback.dto.response.board.GetBoardResponseDto;
import com.yellowstone.boardback.dto.response.board.PostBoardResponseDto;
import com.yellowstone.boardback.dto.response.board.PutFavoriteResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);


}
