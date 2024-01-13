package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.converter.VerificationConverter;
import com.umc5th.dayrecord.service.VerificationService;
import com.umc5th.dayrecord.web.dto.VerificationDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/verification")
public class VerificationController {
    private final VerificationService verificationService;

    /**
     * 닉네임이 DB에 존재하는지 확인합니다.
     * @param request VerificationDTO.NickNameVerificationRequestDTO
     * @return ApiResponse
     */
    @PostMapping("/nickname-exists")
    public ApiResponse<VerificationDTO.ExistsResponseDTO> nickNameExists(@RequestBody
    @Valid VerificationDTO.NickNameRequestDTO request) {
        boolean result = verificationService.existsNickName(request.getNickName());

        return ApiResponse.onSuccess(VerificationDTO.ExistsResponseDTO.builder()
                .isExists(result)
                .build());
    }

    /**
     * 이메일이 DB에 존재하는지 확인합니다.
     * @param request VerificationDTO.EmailExistsRequestDTO
     * @return ApiResponse
     */
    @PostMapping("/email-exists")
    public ApiResponse<VerificationDTO.ExistsResponseDTO> emailExists(
            @RequestBody @Valid VerificationDTO.EmailRequestDTO request
    ) {
        boolean result = verificationService.existsEmail(request.getEmail());

        return ApiResponse.onSuccess(VerificationDTO.ExistsResponseDTO.builder()
                .isExists(result)
                .build());
    }

    /**
     * 이메일 인증 토큰을 발급합니다. 토큰의 유효 시간은 10분입니다.
     * 여기서 발행한 토큰은 인증 번호와 합께 /verify-email로 전송되어야 합니다.
     * /verify-email에서는 여기서 발행했던 토큰을 검증하여 인증을 완료합니다.
     * @param request VerificationDTO.EmailRequestDTO
     * @return ApiResponse
     */
    @PostMapping("/email-verification-request")
    public ApiResponse<VerificationDTO.EmailCodeVerificationReqResponseDTO> emailVerificationRequest(
            @RequestBody @Valid VerificationDTO.EmailRequestDTO request
    ) {

        VerificationDTO.EmailCodeVerificationReqResponseDTO result =
                // Verification 엔티티 -> responseDTO
                VerificationConverter.VerificationToResponse(
                        // Verification 등록 메서드
                        verificationService.emailCodeVerificationRequest(
                                request.getEmail()
                        )
                );
        return ApiResponse.onSuccess(result);
    }


    /**
     * /email-verification-request에서 발급한 이메일 인증 토큰을, 인증 번호로 검증합니다.
     * 토큰의 유효 시간 10분 이내에 인증이 완료되어야 하며, 유효 시간이 경과한 토큰으로 검증을 시도하면
     * 해당 토큰은 DB에서 삭제되며 인증을 처음부터 다시 시작해야 합니다.
     *
     *
     * @param request 인증 토큰과 인증 번호
     * @return 인증 성공 여부
     */
    @PostMapping("/verify-email")
    public ApiResponse<VerificationDTO.EmailCodeVerificationResponseDTO> verifyEmail(
            @RequestBody @Valid VerificationDTO.EmailCodeVerificationRequestDTO request
    ) {
        boolean verified = verificationService.emailCodeVerificate(request.getToken(), request.getCode());

        VerificationDTO.EmailCodeVerificationResponseDTO result =
                VerificationDTO.EmailCodeVerificationResponseDTO
                .builder()
                .verified(verified)
                .token(request.getToken())
                .build();

        return ApiResponse.onSuccess(result);
    }
}
