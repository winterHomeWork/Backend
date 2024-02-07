package com.anys34.homework.domain.user.service;

import com.anys34.homework.domain.user.domain.User;
import com.anys34.homework.domain.user.domain.repository.UserRepository;
import com.anys34.homework.domain.user.presentation.dto.KakaoInfo;
import com.anys34.homework.global.security.jwt.JwtTokenProvider;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import com.anys34.homework.infra.feign.client.KaKaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final JwtTokenProvider jwtTokenProvider;
    private final KaKaoClient kaKaoClient;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse save(String accessToken) {
        KakaoInfo kaKaoInfo = kaKaoClient.getInfo(accessToken);
            if (!userRepository.existsByEmail(kaKaoInfo.getEmail())) {
                User user = User.builder()
                        .email(kaKaoInfo.getEmail())
                        .name(kaKaoInfo.getName())
                        .build();

                userRepository.save(user);
            }

            return jwtTokenProvider.getToken(kaKaoInfo.getEmail());
    }

}

