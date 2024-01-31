package com.anys34.homework.domain.user.service;

import com.anys34.homework.domain.user.domain.User;
import com.anys34.homework.domain.user.domain.repository.UserRepository;
import com.anys34.homework.domain.user.presentation.dto.KakaoInfo;
import com.anys34.homework.domain.user.presentation.dto.KakaoLoginPageReponse;
import com.anys34.homework.global.security.jwt.JwtTokenProvider;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import com.anys34.homework.infra.feign.client.KaKaoClient;
import com.anys34.homework.infra.feign.client.dto.KaKaoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final JwtTokenProvider jwtTokenProvider;
    private final KaKaoClient kaKaoClient;
    private final UserRepository userRepository;

    @Value("${kakao.authUrl}")
    private String kakaoAuthUrl;

    @Value("${kakao.userApiUrl}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapiKey}")
    private String restapiKey;

    @Value("${kakao.redirectUrl}")
    private String redirectUri;

    @Transactional
    public TokenResponse save(String code) {
        KaKaoToken token = getToken(code);
        try {
            KakaoInfo kaKaoInfo = kaKaoClient.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());

            if (userRepository.existsByEmail(kaKaoInfo.getEmail())) {
                throw new RuntimeException("asdf");
            }
            User user = User.builder()
                    .email(kaKaoInfo.getEmail())
                    .name(kaKaoInfo.getName())
                    .build();

            userRepository.save(user);

            return jwtTokenProvider.getToken(user.getEmail());
        } catch (Exception e) {
            return null;
        }
    }


    private KaKaoToken getToken(final String code) {
        try {
            kaKaoClient.getToken(new URI(kakaoAuthUrl), restapiKey, redirectUri, code, "authorization_code");
        } catch (Exception e) {
            System.out.print("asdf");
        }
        return null;
    }

    @Transactional(readOnly = true)
    public KakaoLoginPageReponse login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String redirectUrl = "https://kauth.kakao.com/oauth/authorize" +
                "?response_type=code&" +
                "client_id=" + restapiKey +
                "&redirect_uri=" + redirectUri;
        return new KakaoLoginPageReponse(redirectUrl);
    }
}

