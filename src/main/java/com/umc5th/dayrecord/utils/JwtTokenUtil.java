package com.umc5th.dayrecord.utils;

import java.io.Serializable;
import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT 토큰 관련 설정을 담당하는 클래스
 * 토큰 발급과 자격 증명 관리
 */
@Component
public class JwtTokenUtil implements Serializable {
    private final SecureRandom secureRandom = new SecureRandom();
    private final Base64.Encoder base64Coder = Base64.getUrlEncoder();

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.issuer}")
    private String issuer;

    // JWT 토큰으로부터 사용자 닉네임 추출
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // JWT 토큰으로부터 만료 일자 추출
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // JWT 토큰으로부터 특정 정보를 추출하는 제네릭 메서드
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 비밀 키를 이용하여 토큰으로부터 정보 검색
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    
    // 토큰 만료 여부 확인
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * UserDetails로부터 임의의 JWT 토큰을 생성합니다.
     * @param userDetails 대상 UserDetails
     * @return JWT 토큰
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }


    /**
     * 임의 문자열로 된 토큰을 반환합니다. JWT 토큰이 아닙니다.
     * @return 임의 문자열로 된 토큰
     */
    public String generateToken() {
        byte[] randomBytes = new byte[20];
        secureRandom.nextBytes(randomBytes);

        return base64Coder.encodeToString(randomBytes);
    }



    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key)
                .compact();
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 토큰 검증
    // 로그인 시 입력된 사용자 정보와 토큰 상의 사용자 정보가 일치한지
    // + 토큰 유효기간이 지나지 않았는지
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}