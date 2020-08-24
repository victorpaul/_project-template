package com.projecttemplategroup.projecttemplateartifact.security

import com.projecttemplategroup.projecttemplateartifact.enums.UserRoleEnum
import org.pac4j.core.config.Config
import org.pac4j.springframework.annotation.AnnotationConfig
import org.pac4j.springframework.component.ComponentConfig
import org.pac4j.springframework.web.SecurityInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{ComponentScan, Configuration, Import}
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, WebMvcConfigurer}

@Configuration
@Import(Array(classOf[ComponentConfig], classOf[AnnotationConfig]))
@ComponentScan(Array("org.pac4j.springframework.web"))
class SecurityConfig(
                      @Value("${security.tokenname}") tokenName: String,
                      config: Config
                    ) extends WebMvcConfigurer {

  override def addInterceptors(registry: InterceptorRegistry): Unit = {

    registry.addInterceptor(new CorsConfigs(Seq(tokenName)))

    registry.addInterceptor(new SecurityInterceptor(config, "DirectBasicAuthClient")).addPathPatterns("/v1/signin")
    registry.addInterceptor(new SecurityInterceptor(config, "CookieClient", UserRoleEnum.USER.name)).addPathPatterns("/v1-usr/**")
    registry.addInterceptor(new SecurityInterceptor(config, "CookieClient", UserRoleEnum.ADMIN.name)).addPathPatterns("/v1-adm/**")
  }
}
