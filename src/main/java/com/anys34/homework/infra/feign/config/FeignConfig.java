package com.anys34.homework.infra.feign.config;

import feign.Client;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = {"com"})
@Configuration
public class FeignConfig {
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}
