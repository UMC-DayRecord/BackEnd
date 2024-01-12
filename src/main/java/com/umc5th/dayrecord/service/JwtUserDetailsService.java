package com.umc5th.dayrecord.service;

import java.util.ArrayList;

import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * DB로부터 UserDetails를 얻어와 AuthenticationManager에게 전달하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("JwtUserDetailsService: loadUserByUsername");

        // DB로부터 닉네임을 기준으로 사용자 조회
        User searchResult = userRepository.getUserByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));


        // DB로부터 불러온 UserDetails 반환
        logger.info("JwtUserDetailsService: returning userDetails from DB");
        return new org.springframework.security.core.userdetails.User(
                searchResult.getNickname(),
                searchResult.getPassword(),
                new ArrayList<>()
        );
    }
}