package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.service.UserCommandService;
import com.umc5th.dayrecord.utils.JwtTokenUtil;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/users")
@CrossOrigin
public class UserContoller {
    private final UserDetailsService userDetailsService;
//    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 사용자 회원 가입
     * @param request UserDTO.UserRegisterRequestDTO
     * @return ApiResponse
     */
    @PostMapping("")
    public ApiResponse<UserDTO.UserRegisterResponseDTO> register(
            @RequestBody @Valid UserDTO.UserRegisterRequestDTO request) {
        UserDTO.UserRegisterResponseDTO result = userCommandService.register(request);
        return ApiResponse.onSuccess(result);
    }


    /**
     * 로그인
     * @param request UserDTO.UserLoginRequestDTO
     * @return ApiResponse
     * @throws Exception Exception
     */
    @PostMapping("/login")
    public ApiResponse<UserDTO.UserLoginResponseDTO> createAuthenticationToken(
            @RequestBody @Valid UserDTO.UserLoginRequestDTO request
    ) throws Exception {
        //TODO: 응답 내용에 토큰 만료 시각, 로그인 시각 추가

        // 인증 절차 수행
        this.authenticate(request.getNickName(), request.getPassword());

        // DB로부터 request에 대응하는 userDetails 가져오기
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getNickName());

        // 가져온 userDetails를 기반으로 새 토큰 생성
        final String token = jwtTokenUtil.generateToken(userDetails);

        // 응답 반환
        return ApiResponse.onSuccess(
                UserDTO.UserLoginResponseDTO
                        .builder()
                        .token(token)
                        .nickName(userDetails.getUsername())
                        .build()
        );
    }


    /**
     *
     * @param username 사용자 닉네임
     * @param password 비밀번호
     * @throws Exception Exception
     */
    private void authenticate(String username, String password) throws Exception {
        logger.info("authenticate");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
