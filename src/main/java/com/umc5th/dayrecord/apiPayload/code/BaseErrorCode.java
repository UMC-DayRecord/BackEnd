package com.umc5th.dayrecord.apiPayload.code;

import com.umc5th.dayrecord.apiPayload.code.ErrorReasonDTO;

public interface BaseErrorCode {
    public ErrorReasonDTO getReason();
    public ErrorReasonDTO getReasonHttpStatus();
}
