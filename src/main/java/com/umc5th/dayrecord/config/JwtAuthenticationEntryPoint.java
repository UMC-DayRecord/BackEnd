package com.umc5th.dayrecord.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 허가되지 않은 동작을 하는 경우, 접근 불가 메시지를 표시
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {


        // TODO: 접근 불가 메시지를 공통 API 응답에 담을 수 있는지?
//        ApiResponse<HttpServletRequest> apiResponse = ApiResponse.onFailure(
//                ErrorStatus._UNAUTHORIZED.getCode(),
//                "허가되지 않은 요청입니다.",
//                request
//        );
//        String jsonResponse = new ObjectMapper().writeValueAsString(apiResponse);
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getOutputStream().println(jsonResponse);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}