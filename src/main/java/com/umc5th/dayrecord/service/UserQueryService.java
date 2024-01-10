package com.umc5th.dayrecord.service;

public interface UserQueryService {
    boolean isDuplicateEmail(String email);
    boolean isDuplicatePhoneNumber(String phoneNumber);
    boolean isDuplicateNickName(String nickName);
    boolean existId(Long userId);
}
