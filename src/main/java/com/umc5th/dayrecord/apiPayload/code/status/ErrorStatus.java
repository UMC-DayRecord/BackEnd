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

    // Page Error
    _PAGE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "PAGE401", "옳지 않은 페이지 번호입니다."),

    _QUERY_BAD_REQUEST(HttpStatus.BAD_REQUEST, "QUERY401", "검색어를 2글자 이상 입력해주세요."),

    _USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "사용자를 찾을 수 없습니다."),

    _LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "USER401", "아이디 또는 비밀번호가 맞지 않습니다."),

    _VERIFICATION_BAD_REQUEST(HttpStatus.BAD_REQUEST, "VERIFICATION400", "잘못된 인증 코드입니다."),
    _VERIFICATION_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "VERIFICATION404", "인증 정보가 없습니다."),
    _VERIFICATION_REQUEST_TIMED_OUT(HttpStatus.REQUEST_TIMEOUT, "VERIFICATION408", "인증 요청이 만료되었습니다."),
    _VERIFICATION_REQUEST_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "VERIFICATION401", "인증이 아직 완료되지 않았거나, 인증 번호가 틀렸습니다."),
    _VERIFICATION_REQUEST_EMAIL_SEND_FAILED(HttpStatus.BAD_REQUEST, "VERIFICATION409", "이메일 전송에 실패했습니다. 이메일 주소가 올바른지 확인하세요."),

    _JWT_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "LOGIN4000", "JWT 토큰의 유효 기간이 경과했습니다."),
    _JWT_TOKEN_VERIFICATION_FAILED(HttpStatus.BAD_REQUEST, "LOGIN4001", "JWT 토큰 검증에 실패했습니다."),

    _POST_NOT_FOUNT(HttpStatus.NOT_FOUND, "POST404", "일기를 찾을 수 없습니다."),
    _STREAM_NOT_FOUNT(HttpStatus.NOT_FOUND, "STREAM404", "스트림를 찾을 수 없습니다.");


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
