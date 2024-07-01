package com.yellowstone.boardback.exception;

import com.yellowstone.boardback.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadReqestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException .class})
    public ResponseEntity<ResponseDto> validationExceptionHandler(Exception exception) {
        return ResponseDto.validationFailed();
    }

}