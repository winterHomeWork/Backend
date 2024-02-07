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

    @Value("${kakao.restapiKey}")
    private String restapiKey;

    @Value("${kakao.redirectUrl}")
    private String redirectUri;

    @Transactional
    public TokenResponse save(String code) {
        KaKaoToken token = getToken(code);
            KakaoInfo kaKaoInfo = kaKaoClient.getInfo(token.getTokenType() + " " + token.getAccessToken());

            if (!userRepository.existsByEmail(kaKaoInfo.getEmail())) {
                User user = User.builder()
                        .email(kaKaoInfo.getEmail())
                        .name(kaKaoInfo.getName())
                        .build();

                userRepository.save(user);
            }

            return jwtTokenProvider.getToken(kaKaoInfo.getEmail());
    }


    private KaKaoToken getToken(String code) {
        return kaKaoClient.getToken(restapiKey, redirectUri, code, "authorization_code");
    }

}

