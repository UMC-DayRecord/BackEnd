package com.umc5th.dayrecord.repository;

import com.umc5th.dayrecord.domain.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {

}