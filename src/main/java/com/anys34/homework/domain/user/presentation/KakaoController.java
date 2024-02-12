package com.anys34.homework.domain.user.presentation;

import com.anys34.homework.domain.user.presentation.dto.CodeRequest;
import com.anys34.homework.domain.user.service.KakaoAuthLinkService;
import com.anys34.homework.domain.user.service.KakaoAuthService;
import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "kakao login")
@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {
    
    private final KakaoAuthLinkService kakaoAuthLinkService;
    private final KakaoAuthService kakaoAuthService;

    @Operation(summary = "카카오 로그인 링크 리턴")
    @GetMapping
    public String getKakaoAuthLink() {
        return kakaoAuthLinkService.execute();
    }

    @Operation(summary = "카카오 정보 불러와서 데이터 저장")
    @PostMapping
    public TokenResponse login(@RequestBody CodeRequest codeRequest) {
        return kakaoAuthService.execute(codeRequest);
    }
}
