package com.umc5th.dayrecord.service;

public interface VerificationService {
    boolean existsNickName(String nickName);
    boolean existsEmail(String email);
    String emailCodeVerificationRequest(String email);

    boolean emailCodeverificate(String token, String code);
    boolean isTokenVerificated(String token);

}
