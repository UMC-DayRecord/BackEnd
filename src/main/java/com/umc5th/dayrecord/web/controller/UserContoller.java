package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.service.UserService;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/users")
public class UserContoller {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 사용자 회원 가입
     * @param request UserDTO.UserRegisterRequestDTO
     * @return
     */
    @PostMapping("")
    public ApiResponse<UserDTO.UserRegisterResponseDTO> register(@RequestBody UserDTO.UserRegisterRequestDTO request) {
        UserDTO.UserRegisterResponseDTO result = userService.register(request);
        return ApiResponse.onSuccess(result);
    }
}
