package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.domain.User;

import java.util.Optional;

public interface UserQueryService {
    boolean isDuplicateEmail(String email);
    boolean isDuplicatePhoneNumber(String phoneNumber);
    boolean isDuplicateNickName(String nickName);
    Optional<User> getUser(Long userId);
    Optional<User> getUser(String email, String name);
    Optional<User> getUser(String nickName);
    boolean existId(Long userId);

    boolean isCurrentUser(String nickName);

    Optional<String> getLoggedInUserNickName();
}
