package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByPhoneNumber(String phoneNumber);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByNickname(String nickName);

    Boolean existsUserById(Long userId);
    
}
