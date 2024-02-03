package com.anys34.homework.global.security.jwt.exception;

import com.anys34.homework.global.error.exception.BusinessException;
import com.anys34.homework.global.error.exception.ErrorCode;

public class ExpiredJwtException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}