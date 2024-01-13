package com.umc5th.dayrecord.domain;

import com.umc5th.dayrecord.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.Random;


@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class Verification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    // 인증 코드
    @Column(length = 10, nullable = false)
    @Builder.Default
    private String code = Integer.toString( new Random().nextInt(888888) + 111111);

    @Column(nullable = false)
    @Builder.Default
    private Boolean isSuccess = false;

    // 기본 만료 시각은 10분 뒤임
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime expireAt = LocalDateTime.now().plusMinutes(10);
}
