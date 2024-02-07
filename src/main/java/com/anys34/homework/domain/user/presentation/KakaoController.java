package com.anys34.homework.domain.user.presentation;

import com.anys34.homework.domain.user.service.KakaoService;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "kaka login")
@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;

    @Operation(summary = "카카오 정보 불러와서 데이터 저장")
    @GetMapping("/save")
    public TokenResponse save(@RequestParam(name = "access_token") String token) {
        return kakaoService.save(token);
    }

}
