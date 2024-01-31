package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.DiaryPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryPhotoRepository extends JpaRepository<DiaryPhoto, Long> {
}
