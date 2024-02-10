package com.anys34.homework.infra.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.anys34.homework.infra.feign.client"})
public class FeignConfig {
}
