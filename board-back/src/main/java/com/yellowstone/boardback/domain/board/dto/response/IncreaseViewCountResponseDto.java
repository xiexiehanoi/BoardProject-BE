package com.yellowstone.boardback.domain.board.dto.response;

import com.yellowstone.boardback.common.ResponseCode;
import com.yellowstone.boardback.common.ResponseMessage;
import com.yellowstone.boardback.common.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IncreaseViewCountResponseDto extends ResponseDto {

    private IncreaseViewCountResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<IncreaseViewCountResponseDto> succeess(){
        IncreaseViewCountResponseDto result = new IncreaseViewCountResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
