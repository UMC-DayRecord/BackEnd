package com.umc5th.dayrecord.service;


import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public boolean isDuplicateEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean isDuplicatePhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isDuplicateNickName(String nickName) {
        return userRepository.existsUserByNickname(nickName);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.getUserById(userId);
    }
    @Override
    public Optional<User> getUser(String email, String name) {
        return userRepository.getUserByEmailAndName(email, name);
    }

    @Override
    public boolean existId(Long userId) {
        return userRepository.existsById(userId);
    }

    /**
     * 현재 로그인한 사용자의 닉네임과 파라미터로 받은 닉네임이 일치하는지 검사합니다.
     * @param username 검증할 사용자의 닉네임
     * @return 일치 여부, 로그인 상태가 아닌 경우 false
     */
    @Override
    public boolean isCurrentUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 로그인하지 않은 경우 false
        if(authentication == null) return false;

        String currentUserUsername = authentication.getName();

        return currentUserUsername.equals(username);
    }
}
