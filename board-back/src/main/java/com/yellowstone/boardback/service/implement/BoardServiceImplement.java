package com.yellowstone.boardback.service.implement;

import com.yellowstone.boardback.dto.request.board.PostBoardRequestDto;
import com.yellowstone.boardback.dto.response.ResponseDto;
import com.yellowstone.boardback.dto.response.board.PostBoardResponseDto;
import com.yellowstone.boardback.entity.BoardEntity;
import com.yellowstone.boardback.entity.ImageEntity;
import com.yellowstone.boardback.repository.BoardRepository;
import com.yellowstone.boardback.repository.ImageRepository;
import com.yellowstone.boardback.repository.UserRepository;
import com.yellowstone.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try{

            boolean existedEmail = userRepository.existsByEmail(email);
            if(!existedEmail) return PostBoardResponseDto.notExistUser();


            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }
}
