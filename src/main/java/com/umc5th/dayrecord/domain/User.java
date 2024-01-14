package com.umc5th.dayrecord.domain;


import com.umc5th.dayrecord.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Setter
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    //TODO: updatable = false 속성 추가
    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(length = 15, unique = true, nullable = false)
    private String phoneNumber;

    @Column(length = 30, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean streamPurchase;

    @Column(nullable = false)
    private Boolean addBlockPurchase;

    @Column(nullable = false)
    private Boolean autoIndexPurchase;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long streamCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Likes> likesList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Stream> streamList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPhoto userPhoto;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comment;
}
