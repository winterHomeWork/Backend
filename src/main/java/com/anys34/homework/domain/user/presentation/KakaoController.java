package com.anys34.homework.domain.user.presentation;

import com.anys34.homework.domain.user.presentation.dto.KakaoLoginPageReponse;
import com.anys34.homework.domain.user.service.KakaoService;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;

    @GetMapping("/save")
    public TokenResponse save(String code) {
        return kakaoService.save(code);
    }

    @GetMapping
    public KakaoLoginPageReponse login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return kakaoService.login(httpServletRequest, httpServletResponse);
    }
}
