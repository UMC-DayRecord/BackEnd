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
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detail;

    private Boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stream_id")
    private Stream stream;


    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<DiaryPhoto> diaryPhotoList;

    public void update(String detail) {
        this.detail = detail;
    }

    public void updateVisible(Boolean isPublic) {
        this.isPublic = isPublic;
    }
}
