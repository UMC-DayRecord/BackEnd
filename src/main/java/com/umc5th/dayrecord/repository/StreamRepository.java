package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

    @Query("SELECT s FROM Stream s WHERE (s.user_id <> :userId)")
    Slice<Stream> findAllByUserId(Long userId, PageRequest pageRequest);

}