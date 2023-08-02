package com.myadd.myadd.user.security.config;

import com.myadd.myadd.user.security.handler.CustomAuthenticationFailureHandler;
import com.myadd.myadd.user.security.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests()
                .antMatchers("/posts/**").authenticated()
                .antMatchers("/users/my-info/**").authenticated()
                .antMatchers("/users/change-password/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/users/login/email") // 시큐리티가 /login 경로로 로직을 만들어서 시큐리티 로그인을 처리함
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .usernameParameter("email");
        httpSecurity.logout()
                .logoutUrl("/users/my-info/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.getWriter().write("{\"message\": \"Logout success!\"}");
                    }
                });


        return httpSecurity.build();
    }
}