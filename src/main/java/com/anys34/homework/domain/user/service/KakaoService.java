package com.anys34.homework.domain.user.service;

import com.anys34.homework.domain.user.domain.User;
import com.anys34.homework.domain.user.domain.repository.UserRepository;
import com.anys34.homework.domain.user.presentation.dto.KakaoInfo;
import com.anys34.homework.global.security.jwt.JwtTokenProvider;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import com.anys34.homework.infra.feign.client.KaKaoClient;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final JwtTokenProvider jwtTokenProvider;
    private final KaKaoClient kaKaoClient;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse save(String accessToken) throws ParseException {
        String info = kaKaoClient.getInfo("Bearer " + accessToken);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(info);

        JSONObject kakao_account = (JSONObject) jsonObject.get("kakao_account");
        String email = kakao_account.get("email").toString();

        JSONObject properties = (JSONObject) jsonObject.get("properties");
        String nickname = properties.get("nickname").toString();

        KakaoInfo kakaoInfo = new KakaoInfo(email, nickname);

        if (!userRepository.existsByEmail(kakaoInfo.getEmail())) {
            User user = User.builder()
                    .email(kakaoInfo.getEmail())
                    .name(kakaoInfo.getName())
                    .build();
            userRepository.save(user);
        }

        return jwtTokenProvider.getToken(kakaoInfo.getEmail());
    }
}

