package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.isPublic = true AND (p.user.id <> :userId)")
    Slice<Post> findByPost(@Param("userId") Long userId, PageRequest pageRequest);
}
