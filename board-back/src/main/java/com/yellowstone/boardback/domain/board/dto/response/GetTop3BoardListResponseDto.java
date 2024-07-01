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
public class GetTop3BoardListResponseDto extends ResponseDto {

    private List<BoardListItem> top3List;

    private GetTop3BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
