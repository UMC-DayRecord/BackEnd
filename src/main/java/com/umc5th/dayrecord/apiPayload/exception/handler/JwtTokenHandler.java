package com.umc5th.dayrecord.apiPayload.exception.handler;

import com.umc5th.dayrecord.apiPayload.code.BaseErrorCode;
import com.umc5th.dayrecord.apiPayload.exception.GeneralException;

public class JwtTokenHandler extends GeneralException {
    public JwtTokenHandler(BaseErrorCode code) {
        super(code);
    }
}
