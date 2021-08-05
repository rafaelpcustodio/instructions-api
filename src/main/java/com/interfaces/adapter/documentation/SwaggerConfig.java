package com.interfaces.adapter.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    Docket memberGetMemberApi() {
        return new Docket(SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(buildApiInfo())
                .select()
                .apis(basePackage("com.hiring"))
                .paths(any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Instructions Challenge")
                .version("1.0.0")
                .build();
    }
}
