package com.umc5th.dayrecord.apiPayload.exception.handler;

import com.umc5th.dayrecord.apiPayload.code.BaseErrorCode;
import com.umc5th.dayrecord.apiPayload.exception.GeneralException;

public class EmailHandler extends GeneralException {
    public EmailHandler(BaseErrorCode code) {
        super(code);
    }
}
