package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Likes;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByPostAndUser(Post post, User user);
}
