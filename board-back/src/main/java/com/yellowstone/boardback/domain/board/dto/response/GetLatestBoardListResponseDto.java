package com.yellowstone.boardback.domain.board.dto.response;

import com.yellowstone.boardback.common.ResponseCode;
import com.yellowstone.boardback.common.ResponseMessage;
import com.yellowstone.boardback.domain.board.dto.object.BoardListItem;
import com.yellowstone.boardback.common.ResponseDto;
import com.yellowstone.boardback.domain.board.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetLatestBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> latestList;
    private GetLatestBoardListResponseDto(List<BoardListViewEntity> boardEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = BoardListItem.getList(boardEntities);
    }
    public static ResponseEntity<GetLatestBoardListResponseDto> succeess(List<BoardListViewEntity> boardEntity){
        GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(boardEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
