package com.ssafy.checksource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * 다른 서버에 Rest요청을 할 때 필요한 설정을 해주는 클래스
 */

@Configuration
public class RestTemplateConfig {

	
	//application.yml파일에 적어놓은 세팅 사용
	@Value("${spring.restTemplate.factory.readTimeout}")
    private int READ_TIMEOUT;

    @Value("${spring.restTemplate.factory.connectTimeout}")
    private int CONNECT_TIMEOUT;

    @Value("${spring.restTemplate.httpClient.maxConnTotal}")
    private int MAX_CONN_TOTAL;

    @Value("${spring.restTemplate.httpClient.maxConnPerRoute}")
    private int MAX_CONN_PER_ROUTE;

    @Bean //bean으로 생성해 DI주입
    public RestTemplate restTemplate() {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();

        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }
}
