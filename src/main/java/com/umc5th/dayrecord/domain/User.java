package com.umc5th.dayrecord.domain;


import com.umc5th.dayrecord.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private String password;

    private String phoneNumber;

    private Boolean streamPurchase;

    private Boolean addBlockPurchase;

    private Boolean autoIndexPurchase;

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
