package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Diary findByStream(Stream stream);
}
