package com.anys34.homework.infra.feign.client.food.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HttpResponse {
    String statusCode;
    String contentType;
    ResponseBody body;
}
