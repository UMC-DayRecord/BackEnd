package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.RegisterHandler;
import com.umc5th.dayrecord.converter.UserConverter;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public UserDTO.UserRegisterResponseDTO register(UserDTO.UserRegisterRequestDTO request) {
        // 중복된 이메일 또는 전화번호인 경우 처리
        if(isDuplicateEmail(request.getEmail())) throw new RegisterHandler(ErrorStatus._EMAIL_DUPLICATE);
        if(isDuplicatePhoneNumber(request.getPhoneNumber())) throw new RegisterHandler(ErrorStatus._PHONE_NUMBER_DUPLICATE);
        if(isDuplicateNickName(request.getNickName())) throw new RegisterHandler(ErrorStatus._NICKNAME_DUPLICATE);

        // 비밀번호 암호화 진행
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        Optional<User> result =  Optional.of(userRepository.save(UserConverter.RegisterRequestToUser(request)));
        return UserConverter.UserToResponse(result.get());
    }

    // 중복 여부를 감지하는 내부 메서드
    private boolean isDuplicateEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    private boolean isDuplicatePhoneNumber(String phoneNumber) {
         return userRepository.existsUserByPhoneNumber(phoneNumber);
    }

    private boolean isDuplicateNickName(String nickName) {
        return userRepository.existsUserByNickname(nickName);
    }
}
