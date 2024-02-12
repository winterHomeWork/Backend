package com.anys34.homework.domain.user.service;

import com.anys34.homework.domain.user.domain.User;
import com.anys34.homework.domain.user.domain.repository.UserRepository;
import com.anys34.homework.domain.user.presentation.dto.CodeRequest;
import com.anys34.homework.global.config.properties.AuthProperties;
import com.anys34.homework.global.security.jwt.JwtTokenProvider;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import com.anys34.homework.infra.feign.client.KakaoAuthClient;
import com.anys34.homework.infra.feign.client.KakaoInformationClient;
import com.anys34.homework.infra.feign.client.dto.res.KakaoAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {
    private final AuthProperties authProperties;
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoInformationClient kakaoInformationClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public TokenResponse execute(CodeRequest codeRequest) {
        KakaoAuthResponse accessToken = kakaoAuthClient.getAccessToken(
                authProperties.getKakaoClientId(),
                authProperties.getKakaoRedirectUrl(),
                codeRequest.getCode());

        Map<String, Object> response = kakaoInformationClient.getUserInformation("Bearer " + accessToken.getAccess_token());

        Map<String, Object> kakao_account = (Map<String, Object>) response.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakao_account.get("email");

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            userRepository.save(User.builder()
                    .email(email)
                    .name(nickname)
                    .build());
        }

        return jwtTokenProvider.getToken(email);
    }
}
