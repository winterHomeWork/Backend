package com.anys34.homework.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("auth")
public class AuthProperties {

    private Kakao kakao;

    @Getter
    @Setter
    public static class Kakao {
        private String baseUrl;
        private String clientId;
        private String javascriptId;
        private String redirectUrl;
    }

    public String getKakaoBaseUrl() {
        return kakao.getBaseUrl();
    }

    public String getKakaoClientId() {
        return kakao.getClientId();
    }

    public String getKakaoJavascriptId() {
        return kakao.getJavascriptId();
    }

    public String getKakaoRedirectUrl() {
        return kakao.getRedirectUrl();
    }
}
