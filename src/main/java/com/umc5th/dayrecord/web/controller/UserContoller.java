package com.umc5th.dayrecord.web.controller;

import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
import com.umc5th.dayrecord.apiPayload.exception.handler.VerificationHandler;
import com.umc5th.dayrecord.service.UserCommandService;
import com.umc5th.dayrecord.service.UserQueryService;
import com.umc5th.dayrecord.service.VerificationService;
import com.umc5th.dayrecord.utils.JwtTokenUtil;
import com.umc5th.dayrecord.web.dto.UserDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
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
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final VerificationService verificationService;

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

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
     */
    @PostMapping("/login")
    public ApiResponse<UserDTO.UserLoginResponseDTO> createAuthenticationToken(
            @RequestBody @Valid UserDTO.UserLoginRequestDTO request) {
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
     * 이메일 주소를 입력받아 사용자 닉네임 찾기, 이메일로 해당 사용자의 닉네임을 보냄
     * @param request UserDTO.FindMyIdRequestDTO
     * @return ApiResponse
     */
    @PostMapping("/findmyid")
    public ApiResponse<UserDTO.FindMyIdResponseDTO> findMyId(
            @RequestBody @Valid UserDTO.FindMyIdRequestDTO request
            ) {
        // 이메일 인증 여부 검사
        if(!verificationService.isTokenVerificated(request.getEmailVerificationToken())) {
            // 인증이 되지 않았다면 서비스 단에서 핸들링 됨
            // 인증이 되지 않았는데 여기로 넘어온다면 문제가 있는 상황임
            throw new VerificationHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        }

        // 해당 이메일 주소를 가진 사람이 있는가?
        String nickName = userQueryService
                .getUser(request.getEmail(), request.getName())
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND))
                .getNickname();

        // 찾은 사용자의 닉네임을 담은 이메일 보내기
//        MailDTO.MailSendRequestDTO mailSendRequest =
//                MailDTO.MailSendRequestDTO
//                        .builder()
//                        .title("DayRecord 아이디 찾기")
//                        .content("당신의 아이디는 \"" + nickName + "\" 입니다.")
//                        .targetAddress(request.getEmail())
//                        .build();
//        mailService.sendMessage(mailSendRequest);

        return ApiResponse.onSuccess(UserDTO.FindMyIdResponseDTO.builder().nickName(nickName).build());
    }


    /**
     * 로그인 상태에서 사용자의 비밀 번호 변경
     * @param request UserDTO.ResetPasswordRequestDTO
     * @return 성공 여부
     */
    @PostMapping("/resetpassword")
    public ApiResponse<?> resetPassword(
            @RequestBody @Valid UserDTO.ResetPasswordRequestDTO request
    ) {
        // TODO: 로그인 토큰 유효성 검사

        // 이메일 인증 여부 검사
        if(!verificationService.isTokenVerificated(request.getEmailVerificationToken())) {
            // 인증이 되지 않았다면 서비스 단에서 핸들링 됨
            // 인증이 되지 않았는데 여기로 넘어온다면 문제가 있는 상황임
            throw new VerificationHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        }

        // 모든 토큰이 유효하면 비밀번호 변경 진행
        boolean result = userCommandService.changePassword(request);

        return ApiResponse.onSuccess(result);
    }


    /**
     *
     * @param username 사용자 닉네임
     * @param password 비밀번호
     */
    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new UserNotFoundHandler(ErrorStatus._LOGIN_FAILED);
        }
    }
}
