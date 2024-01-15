package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.RegisterHandler;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
import com.umc5th.dayrecord.converter.UserConverter;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.domain.UserPhoto;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryService userQueryService;

    public UserDTO.UserRegisterResponseDTO register(UserDTO.UserRegisterRequestDTO request) {
        // 중복된 이메일 또는 전화번호인 경우 처리
        if(userQueryService.isDuplicateEmail(request.getEmail())) throw new RegisterHandler(ErrorStatus._EMAIL_DUPLICATE);
        if(userQueryService.isDuplicatePhoneNumber(request.getPhoneNumber())) throw new RegisterHandler(ErrorStatus._PHONE_NUMBER_DUPLICATE);
        if(userQueryService.isDuplicateNickName(request.getNickName())) throw new RegisterHandler(ErrorStatus._NICKNAME_DUPLICATE);

        // 비밀번호 암호화 진행
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = UserConverter.RegisterRequestToUser(request);

        UserPhoto userPhoto = UserPhoto
                .builder()
                .user(user)
                .url(request.getProfilePhoto())
                .build();
        user.setUserPhoto(userPhoto);

        Optional<User> result =  Optional.of(userRepository.save(user));

        return UserConverter.UserToResponse(result.get());
    }

    public boolean changePassword(UserDTO.ResetPasswordRequestDTO request) {
        User user = userRepository
                .getUserByEmailAndNicknameAndName(request.getEmail(), request.getNickName(), request.getName())
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        // 새 비밀번호 암호화 진행
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // 변경 사항 저장
        userRepository.save(user);
        return true;
    }
}
