package com.yellowstone.boardback.service.implement;

import com.yellowstone.boardback.dto.request.board.PostBoardRequestDto;
import com.yellowstone.boardback.dto.response.ResponseDto;
import com.yellowstone.boardback.dto.response.board.PostBoardResponseDto;
import com.yellowstone.boardback.entity.BoardEntity;
import com.yellowstone.boardback.repository.BoardRepository;
import com.yellowstone.boardback.repository.UserRepository;
import com.yellowstone.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try{

            boolean existeEmail = userRepository.existsByEmail(email);
            if(!existeEmail) return PostBoardResponseDto.notExistUser();


            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }
}
