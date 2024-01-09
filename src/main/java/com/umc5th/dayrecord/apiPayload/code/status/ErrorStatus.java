package com.umc5th.dayrecord.apiPayload.code.status;

import com.umc5th.dayrecord.apiPayload.code.BaseErrorCode;
import com.umc5th.dayrecord.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 오류"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    _NOT_FOUND(HttpStatus.FORBIDDEN, "COMMON404", "요청을 찾을 수 없습니다."),

    _PHONE_NUMBER_DUPLICATE(HttpStatus.BAD_REQUEST, "REGISTER401", "이미 등록된 전화번호입니다."),
    _EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST, "REGISTER402", "이미 등록된 이메일 주소입니다."),
    _NICKNAME_DUPLICATE(HttpStatus.BAD_REQUEST, "REGISTER403", "이미 등록된 닉네임입니다."),

    _USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "사용자를 찾을 수 없습니다.")
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
