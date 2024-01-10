package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByPhoneNumber(String phoneNumber);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByNickname(String nickName);

    Optional<User> getUserByEmailAndName(String email, String name);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByNickname(String nickName);
}
