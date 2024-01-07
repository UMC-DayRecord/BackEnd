package com.umc5th.dayrecord.converter;

import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.domain.UserPhoto;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

public class UserConverter {

    // UserRegisterRequestDTO -> User
    @Builder
    public static User RegisterRequestToUser(UserDTO.UserRegisterRequestDTO request) {
        return User.builder()
                .name(request.getName())
                .nickname(request.getNickName())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .streamPurchase(false)
                .addBlockPurchase(false)
                .autoIndexPurchase(false)
                .streamCount(0L)
//                .userPhoto(request.getUserPhoto())

                .likesList(new ArrayList<>())
                .streamList(new ArrayList<>())
                .comment(new ArrayList<>())
                .build();
    }

    // User -> UserRegisterResponseDTO
    @Builder
    public static UserDTO.UserRegisterResponseDTO UserToResponse(User user) {
        return UserDTO.UserRegisterResponseDTO.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
