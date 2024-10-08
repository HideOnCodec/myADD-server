package com.myadd.myadd.user.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myadd.myadd.response.BaseResponse;
import com.myadd.myadd.response.BaseResponseStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Spring Security: Authentication
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
        emailLoginSuccess(response);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        emailLoginSuccess(response);
    }

    public void emailLoginSuccess(HttpServletResponse response) throws IOException {
        BaseResponse<BaseResponseStatus> successResponse = new BaseResponse<>(BaseResponseStatus.SUCCESS_EMAIL_LOGIN);
        String jsonResponse = new ObjectMapper().writeValueAsString(successResponse);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    public String oauth2LoginSuccess(HttpServletResponse response, String provider) throws IOException {
        BaseResponse<BaseResponseStatus> successResponse = null;

        if(provider.equals("google"))
            successResponse = new BaseResponse<>(BaseResponseStatus.SUCCESS_GOOGLE_LOGIN);
        else if(provider.equals("kakao"))
            successResponse = new BaseResponse<>(BaseResponseStatus.SUCCESS_KAKAO_LOGIN);

        String jsonResponse = new ObjectMapper().writeValueAsString(successResponse);

         response.setStatus(HttpServletResponse.SC_OK);
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(jsonResponse);

        return jsonResponse;
    }
}
