package com.anys34.homework.global.error;

import com.anys34.homework.global.error.exception.BusinessException;
import com.anys34.homework.global.error.exception.ErrorCode;
import com.anys34.homework.global.error.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 비즈니스 로직에서의 에러
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {

        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

}
