package com.anys34.homework.domain.user.service;

import com.anys34.homework.domain.user.presentation.dto.AccessTokenResponse;
import com.anys34.homework.domain.user.presentation.dto.RefreshTokenRequest;
import com.anys34.homework.global.security.jwt.JwtTokenProvider;
import com.anys34.homework.global.security.jwt.entity.RefreshToken;
import com.anys34.homework.global.security.jwt.entity.RefreshTokenRepository;
import com.anys34.homework.global.security.jwt.exception.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateAccessTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public AccessTokenResponse execute(RefreshTokenRequest token) {
        System.out.println(token.getRefreshToken());
        RefreshToken refreshToken = getRefreshToken(token.getRefreshToken());
        return new AccessTokenResponse(jwtTokenProvider
                .createAccessToken(refreshToken.getEmail()));
    }

    private RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findById(token)
                .orElseThrow(() -> ExpiredJwtException.EXCEPTION);
    }
}
