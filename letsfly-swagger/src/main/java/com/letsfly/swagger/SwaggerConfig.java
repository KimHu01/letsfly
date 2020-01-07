package com.letsfly.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
@Configuration
@ComponentScan("com.**")
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
    
    /** 是否启用swagger */
    @Value("${swagger.enable:true}")
    private boolean enable;
    
    /** 文档标题 */
    @Value("${swagger.title:Abel Title}")
    private String title;
    
    /** 文档描述 */
    @Value("${swagger.description:desc of Abel}")
    private String description;
    
    /** 文档版本 */
    @Value("${swagger.version:1.0.0}")
    private String version;
    
    /** 服务条款URL */
    @Value("${swagger.terms.of.service.url:}")
    private String termsOfServiceUrl;
    
    /** 许可证 */
    @Value("${swagger.license:}")
    private String license;
    
    /** 许可证URl */
    @Value("${swagger.license.url:}")
    private String licenseUrl;
    
    /**
     * Docket
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(this.apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }
    
    /**
     * apiInfo
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .termsOfServiceUrl(termsOfServiceUrl)
                .license(license)
                .licenseUrl(licenseUrl)
                .build();
    }
}
