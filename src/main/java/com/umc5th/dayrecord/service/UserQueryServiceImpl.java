package com.umc5th.dayrecord.service;


import com.umc5th.dayrecord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public boolean isDuplicateEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public boolean isDuplicatePhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }

    public boolean isDuplicateNickName(String nickName) {
        return userRepository.existsUserByNickname(nickName);
    }

}
