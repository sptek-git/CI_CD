package org.keumann.wisestudy.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.keumann.wisestudy.dto.RestfulConfigDto;
import org.keumann.wisestudy.properties.RestfulProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


//@Configuration
@EnableConfigurationProperties(RestfulProperties.class)
public class RestfulConfig {

    @Value("${restemplate.readTimeout}")
    private int READ_TIMEOUT;

    @Value("${restemplate.connectTimeout}")
    private int CONNECT_TIMEOUT;

    @Value("${restemplate.maxConnTotal}")
    private int MAX_CONN_TOTAL;

    @Value("${restemplate.maxConnPerRoute}")
    private int MAX_CONN_PER_ROUTE;

    @Bean
    @ConditionalOnMissingBean
    public RestfulConfigDto restfulConfigDto(RestfulProperties restfulProperties) {

        RestfulConfigDto restfulConfigDto = new RestfulConfigDto();
        restfulConfigDto.setConnectTimeout(restfulProperties.getConnectTimeout());
        restfulConfigDto.setReadTimeout(restfulProperties.getReadTimeout());
        restfulConfigDto.setMaxConnPerRoute(restfulProperties.getMaxConnPerRoute());
        restfulConfigDto.setMaxConnTotal(restfulProperties.getMaxConnTotal());

        return restfulConfigDto;
    }


    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();

        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }
}

