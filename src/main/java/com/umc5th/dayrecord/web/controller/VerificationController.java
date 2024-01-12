package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
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
    @Valid VerificationDTO.NickNameExistsRequestDTO request) {
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
            @RequestBody @Valid VerificationDTO.EmailExistsRequestDTO request
    ) {
        boolean result = verificationService.existsEmail(request.getEmail());

        return ApiResponse.onSuccess(VerificationDTO.ExistsResponseDTO.builder()
                .isExists(result)
                .build());
    }
}
