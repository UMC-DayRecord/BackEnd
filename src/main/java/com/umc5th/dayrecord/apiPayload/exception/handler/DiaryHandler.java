package com.umc5th.dayrecord.apiPayload.exception.handler;

import com.umc5th.dayrecord.apiPayload.code.BaseErrorCode;
import com.umc5th.dayrecord.apiPayload.exception.GeneralException;

public class DiaryHandler extends GeneralException {
    public DiaryHandler(BaseErrorCode code) {
        super(code);
    }
}
