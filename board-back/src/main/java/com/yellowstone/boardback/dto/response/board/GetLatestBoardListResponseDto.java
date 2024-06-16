package com.yellowstone.boardback.dto.response.board;

import com.yellowstone.boardback.common.ResponseCode;
import com.yellowstone.boardback.common.ResponseMessage;
import com.yellowstone.boardback.dto.object.BoardListItem;
import com.yellowstone.boardback.dto.response.ResponseDto;
import com.yellowstone.boardback.entity.BoardListViewEntity;
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
