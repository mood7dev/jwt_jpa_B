package com.green.gallery_jwt_jpa.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
/*
@Configuration - bean 등록, Bean 메소드가 있다.
Bean 메소드는 무조건 싱글톤으로 처리된다.
 */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    //Bean 메소드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //람다식
        return http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //security가 session을 사용하지 않는다.
                .httpBasic(httpBasicSpec -> httpBasicSpec.disable()) //시큐리티가 제공해주는 인증 처리 -> 사용 안 함
                .formLogin(formLoginSpec -> formLoginSpec.disable()) //시큐리티가 제공해주는 인증 처리 -> 사용 안 함
                .csrf(csrfSpec -> csrfSpec.disable()) // BE - csrf라는 공격이 있는데 공격을 막는 것이 기본으로 활성화 되어 있는데
                // 세션을 이용한 공격이다. 세션을 어차피 안 쓰니까 비활성화
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/cart").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/item").authenticated()
                        .anyRequest().permitAll()
                )
                .build();
    }

}