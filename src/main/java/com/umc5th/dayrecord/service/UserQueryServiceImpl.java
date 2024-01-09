package com.umc5th.dayrecord.service;


import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public Optional<User> getUser(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public Optional<User> getUser(String email, String nickName) {
        return userRepository.getUserByEmailAndNickname(email, nickName);
    }
}
