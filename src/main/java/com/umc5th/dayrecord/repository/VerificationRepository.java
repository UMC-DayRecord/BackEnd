package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findVerificationByToken(String token);
}
