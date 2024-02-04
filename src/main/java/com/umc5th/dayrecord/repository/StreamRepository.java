package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

    @Query("SELECT p FROM Post p WHERE (p.user.id = :userId) AND (p.stream.id = :streamId)")
    Slice<Post> findByStream(@Param("userId") Long userId, @Param("streamId") Long streamId, PageRequest pageRequest);

    // @Query("SELECT s FROM Stream s WHERE (s.user.id <> 1")
    // @Query("SELECT s FROM Stream s WHERE (s.user.id <> 1)")
    // Slice<Stream> findAllByUserId(@Param("userId") Long userId, PageRequest pageRequest);

    @Query("SELECT p FROM Post p WHERE (p.user.id = :userId) AND (p.detail LIKE %:query% OR p.stream.streamName LIKE %:query%)")
    Slice<Post> findBySearchPost(@Param("userId") Long userId, @Param("query") String query, PageRequest pageRequest);
}