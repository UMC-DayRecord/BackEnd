package com.umc5th.dayrecord.config;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.umc5th.dayrecord.service.JwtUserDetailsService;
import com.umc5th.dayrecord.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * JWT 토큰 검증 필터
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 토큰을 담은 헤더 정보 파싱
        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);

        String username = null;
        String jwtToken = null;
        
        // 토큰 정보 파싱
        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_PREFIX)) {
            // "Bearer " 제거
            jwtToken = requestTokenHeader.substring(TOKEN_PREFIX.length());

            // TODO: 예외 핸들러로 처리
            try {
                // 토큰으로부터 사용자 이름 파싱
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }
            catch (IllegalArgumentException e) {
                System.err.println("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e) {
                System.err.println("JWT Token has expired");
            }
        }
        else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // 토큰 정보를 얻었음 -> 검증 시작
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 토큰에서 얻은 닉네임을 기준으로, DB로부터 UserDetails 가져오기
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            // 토큰과 DB상의 UserDetails가 동일한지 && 토큰이 유효한지 검증
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                // JWT가 유효한 경우 Spring Security의 보안 컨텍스트에 사용자 인증 정보를 설정
                // UsernamePaswordAuthenticationToken을 생성하고, 이것을 보안 컨텍스트에 등록하여 수행됨
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}