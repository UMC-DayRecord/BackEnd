package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.web.dto.UserDTO;

public interface UserCommandService {
    UserDTO.UserRegisterResponseDTO register(UserDTO.UserRegisterRequestDTO request);
    boolean changePassword(UserDTO.ResetPasswordRequestDTO request);
}
