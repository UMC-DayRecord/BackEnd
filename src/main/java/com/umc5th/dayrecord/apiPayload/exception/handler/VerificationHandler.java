package com.umc5th.dayrecord.apiPayload.exception.handler;

import com.umc5th.dayrecord.apiPayload.code.BaseErrorCode;
import com.umc5th.dayrecord.apiPayload.exception.GeneralException;

public class VerificationHandler extends GeneralException {

    public VerificationHandler(BaseErrorCode code) {
        super(code);
    }
}
