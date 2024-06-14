package com.yellowstone.boardback.service;

import com.yellowstone.boardback.dto.request.board.PostBoardRequestDto;
import com.yellowstone.boardback.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
