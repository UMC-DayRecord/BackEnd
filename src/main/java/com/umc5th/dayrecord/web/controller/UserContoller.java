package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.service.UserService;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/users")
public class UserContoller {
    private final UserService userService;

    /**
     * 사용자 회원 가입
     * @param request UserDTO.UserRegisterRequestDTO
     * @return ApiResponse
     */
    @PostMapping("")
    public ApiResponse<UserDTO.UserRegisterResponseDTO> register(@RequestBody @Valid UserDTO.UserRegisterRequestDTO request) {
        UserDTO.UserRegisterResponseDTO result = userService.register(request);
        return ApiResponse.onSuccess(result);
    }
}
