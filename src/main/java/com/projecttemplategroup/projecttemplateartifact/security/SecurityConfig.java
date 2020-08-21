package com.projecttemplategroup.projecttemplateartifact.security;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.annotation.AnnotationConfig;
import org.pac4j.springframework.component.ComponentConfig;
import org.pac4j.springframework.web.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({ComponentConfig.class, AnnotationConfig.class})
@ComponentScan("org.pac4j.springframework.web")
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private Config config;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new SecurityInterceptor(config, "DirectBasicAuthClient"))
                .addPathPatterns("/v1/signin");

        registry.addInterceptor(new SecurityInterceptor(config, "CookieClient", UserRoleEnum.USER.name()))
                .addPathPatterns("/v1-usr/**");
        registry.addInterceptor(new SecurityInterceptor(config, "CookieClient", UserRoleEnum.ADMIN.name()))
                .addPathPatterns("/v1-adm/**");
        registry.addInterceptor(new SecurityInterceptor(config, "CookieClient", UserRoleEnum.ADMIN.name()))
                .addPathPatterns("/v1-sup/**");
    }
}
