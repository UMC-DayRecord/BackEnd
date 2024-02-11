package com.umc5th.dayrecord.domain;

import com.umc5th.dayrecord.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class Stream extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streamName;

    private Boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "stream", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "stream", cascade = CascadeType.ALL)
    private List<StreamPhoto> streamPhotoList;

    @ColumnDefault(value = "'없음'")
    private String keyword;

    public void updateVisible(Boolean isPublic) {
        this.isPublic = isPublic;
    }
}
