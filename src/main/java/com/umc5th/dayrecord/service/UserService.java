package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.converter.UserConverter;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public UserDTO.UserRegisterResponseDTO register(UserDTO.UserRegisterRequestDTO request) {
        // 비밀번호 암호화 진행
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        Optional<User> result =  Optional.of(userRepository.save(UserConverter.RegisterRequestToUser(request)));
        return UserConverter.UserToResponse(result.get());
    }
}
