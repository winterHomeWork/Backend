package com.anys34.homework.global.security.jwt.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
public class RefreshToken {
    @Id
    private String id;

    @Indexed
    private String token;

    @TimeToLive
    private Long ttl;
}
