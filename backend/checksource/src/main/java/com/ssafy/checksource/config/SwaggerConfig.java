package com.ssafy.checksource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.checksource.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo swaggerInfo(){
        return new ApiInfoBuilder()
                .title("Check Source API Documnentation")
                .description(
                        "Check Source API 문서입니다!\n\n백엔드 담당자 :  황다희,윤승환 \n 프론트 담당자 : 남우진,고유창,박은수 \n\n")
                .version("0.2")
                .build();
    }
}