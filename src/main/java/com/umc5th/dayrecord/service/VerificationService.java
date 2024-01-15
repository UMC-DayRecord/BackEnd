package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.domain.Verification;

public interface VerificationService {
    boolean existsNickName(String nickName);
    boolean existsEmail(String email);

    boolean existsUser(String name, String nickName, String email);
    Verification emailCodeVerificationRequest(String email);
    boolean emailCodeVerificate(String token, String code);
    boolean isTokenVerificated(String token);

}
