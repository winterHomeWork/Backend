package com.anys34.homework.infra.feign.config;

import feign.Client;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@EnableFeignClients(basePackages = {"com"})
@Configuration
public class FeignConfig {

    @Bean
    public HttpMessageConverters customConverters() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(converter);
    }
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}
