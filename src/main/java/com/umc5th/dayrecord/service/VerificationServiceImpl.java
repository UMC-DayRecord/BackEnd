package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.VerificationHandler;
import com.umc5th.dayrecord.domain.Verification;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.repository.VerificationRepository;
import com.umc5th.dayrecord.utils.JwtTokenUtil;
import com.umc5th.dayrecord.web.dto.MailDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationServiceImpl implements VerificationService {
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final MailService mailService;
    private final JwtTokenUtil jwtTokenUtil;

    protected final Log logger = LogFactory.getLog(this.getClass());


    /**
     * 입력받은 이메일 주소가 DB에 존재하는지 확인합니다.
     * @param nickName VerificationDTO.EmailVerificationRequestDTO
     * @return 해당 이메일의 등록 여부
     */
    public boolean existsNickName(String nickName) {
        return userRepository.existsUserByNickname(nickName);
    }


    /**
     * 입력받은 이메일 주소가 DB에 존재하는지 확인합니다.
     * @param email VerificationDTO.EmailVerificationRequestDTO
     * @return 해당 이메일의 등록 여부
     */
    public boolean existsEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    /**
     * 입력받은 이메일 주소, 닉네임, 이름을 가진 사용자가 DB에 존재하는지 확인합니다.
     * @param email VerificationDTO.UserRequestDTO
     * @return 해당 이메일의 등록 여부
     */
    public boolean existsEmail(String email, String nickName, String name) {
        return userRepository.existsUserByEmailAndNicknameAndName(email, nickName, name);
    }



    /**
     * 이메일 인증 요청
     * Verification 엔티티에 토큰과 만료일, 인증 성공 여부를 넣고, 토큰을 반환
     *
     */
    public Verification emailCodeVerificationRequest(String email) {
        String token = jwtTokenUtil.generateToken();

        Verification v = Verification.builder()
                .token(token)
                .expireAt(LocalDateTime.now().plusMinutes(10)) // for debugging
                .build();

        mailService.sendMessage(
                MailDTO.MailSendRequestDTO.builder()
                        .title("DayRecord 이메일 인증")
                        .content(
                                "DayRecord\n" +
                                "인증 번호는 \"" + v.getCode() + "\" 입니다.\n" +
                                "시간 내에 입력해 주세요.\n"
                        )
                        .targetAddress(email)
                        .build()
                );

        verificationRepository.save(v);
        logger.debug("Verification code is: " + v.getCode());
        return v;
    }

    /**
     * 요청 토큰과 인증 코드를 받아 인증 여부를 업데이트
     * @param token 요청 토큰
     * @param code 인증 번호
     * @return 인증 절차 성공 여부
     */
    public boolean emailCodeVerificate(String token, String code) {
        Verification v = verificationRepository
                .findVerificationByToken(token)
                .orElseThrow(() -> new VerificationHandler(ErrorStatus._VERIFICATION_REQUEST_NOT_FOUND));

        // 만료되었는지 확인
        if(v.getExpireAt().isBefore(LocalDateTime.now())) {
            // 요청은 지움
            verificationRepository.delete(v);
            // "요청이 만료됨" 예외 발생
            throw new VerificationHandler(ErrorStatus._VERIFICATION_REQUEST_TIMED_OUT);
        }

        // 코드 값이 맞는지 확인
        if(!v.getCode().equals(code)) {
//            verificationRepository.delete(v);
            throw new VerificationHandler(ErrorStatus._VERIFICATION_REQUEST_UNAUTHORIZED);
        }

        // 이미 인증 성공한 요청인 경우
        if(v.getIsSuccess()) {
            logger.info("verification request for token \"" + v.getToken() + "\" is already processed.");
            return true;
        }

        //인증 성공(토큰에 맞는 인증을 찾음 and 만료되지 않음 and 코드 값 일치) Verification.isSuccess -> true
        v.setIsSuccess(true);
        verificationRepository.save(v);

        return v.getIsSuccess();
    }


    /**
     * 인증 여부를 반환하는 메서드, 이 메서드가 호출되면 해당 인증 요청은 사라짐
     * @param token 요청 토큰
     * @return 해당 요청의 인증 여부
     */
    public boolean isTokenVerificated(String token) {
        // TODO: 디버그 기능 제거
        return true;
//        Verification v = verificationRepository.findVerificationByToken(token)
//                .orElseThrow(() -> new VerificationHandler(ErrorStatus._VERIFICATION_REQUEST_NOT_FOUND));
//
//        boolean isSuccess = v.getIsSuccess();
//        LocalDateTime expireAt = v.getExpireAt();
//
//        // 요청은 휘발적(한 번만 성공 여부 조회 가능)
//        verificationRepository.delete(v);
//
//        // 만료되었는지 확인
//        if(expireAt.isBefore(LocalDateTime.now())) {
//            // "요청이 만료됨" 예외 발생
//            throw new VerificationHandler(ErrorStatus._VERIFICATION_REQUEST_TIMED_OUT);
//        }
//
//        // 아직 인증이 완료되지 않은 요청인 경우
//        if (!isSuccess) {
//            // "요청이 완료되지 않음" 예외 발생
//            throw new VerificationHandler(ErrorStatus._VERIFICATION_REQUEST_UNAUTHORIZED);
//        }
//
//        return true;
    }
}
