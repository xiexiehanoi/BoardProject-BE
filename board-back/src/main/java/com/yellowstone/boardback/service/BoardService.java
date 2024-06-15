package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.request.board.PostBoardRequestDto;
import com.yellowstone.boardback.dto.response.board.GetBoardResponseDto;
import com.yellowstone.boardback.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
