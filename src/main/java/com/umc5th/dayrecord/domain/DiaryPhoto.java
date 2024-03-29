package com.umc5th.dayrecord.domain;

import com.umc5th.dayrecord.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class DiaryPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65535, columnDefinition = "TEXT")
    private String url;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public void updateDiary(Diary diary) {
        this.diary = diary;
    }

    public void changeStatus(boolean status) {
        this.status = status;
    }
}
