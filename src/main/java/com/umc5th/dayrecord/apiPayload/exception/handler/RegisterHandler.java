package com.umc5th.dayrecord.apiPayload.exception.handler;

import com.umc5th.dayrecord.apiPayload.code.BaseErrorCode;
import com.umc5th.dayrecord.apiPayload.exception.GeneralException;

public class RegisterHandler extends GeneralException {
    public RegisterHandler(BaseErrorCode code) {
        super(code);
    }
}
