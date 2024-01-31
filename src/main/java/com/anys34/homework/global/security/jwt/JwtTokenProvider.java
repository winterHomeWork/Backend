package com.anys34.homework.global.security.jwt;

import com.anys34.homework.global.security.jwt.dto.TokenResponse;
import com.anys34.homework.global.security.jwt.exception.ExpiredJwtException;
import com.anys34.homework.global.security.principle.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";

    public TokenResponse getToken(String email) {
        String accessToken = generateToken(email, jwtProperties.getAccessExp(),ACCESS_KEY);

        return new TokenResponse(accessToken);
    }

    private String generateToken(String userName, Long expiration) {
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(userName)
                .setHeaderParam("type", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    private String refreshToken(Long ttl) {
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setHeaderParam("type", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttl * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken authorization(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String subject) {
        return getTokenBody(subject).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw ExpiredJwtException.EXCEPTION;
        }
    }
}
