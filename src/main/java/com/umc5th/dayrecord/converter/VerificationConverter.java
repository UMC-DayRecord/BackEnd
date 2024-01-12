package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.Verification;
import com.umc5th.dayrecord.web.dto.VerificationDTO;
import lombok.Builder;


public class VerificationConverter {
    @Builder
    public static VerificationDTO.EmailCodeVerificationReqResponseDTO VerificationToResponse(Verification request) {
        return VerificationDTO.EmailCodeVerificationReqResponseDTO
                .builder()
                .expireAt(request.getExpireAt())
                .token(request.getToken())
                .build();
    }
}
