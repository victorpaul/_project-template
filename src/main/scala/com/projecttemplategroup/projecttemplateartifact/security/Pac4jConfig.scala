package com.projecttemplategroup.projecttemplateartifact.security

import com.projecttemplategroup.projecttemplateartifact.enums.UserRoleEnum
import com.projecttemplategroup.projecttemplateartifact.interfaces.UserWithRole
import com.projecttemplategroup.projecttemplateartifact.security.authenticators.SessionAuthenticator
import com.projecttemplategroup.projecttemplateartifact.security.authenticators.SignInAuthenticator
import com.projecttemplategroup.projecttemplateartifact.utils.SecurityUtils
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer
import org.pac4j.core.client.direct.AnonymousClient
import org.pac4j.core.config.Config
import org.pac4j.core.profile.CommonProfile
import org.pac4j.http.client.direct.DirectBasicAuthClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.pac4j.http.client.direct.CookieClient
import org.springframework.beans.factory.annotation.Value

@Configuration
class Pac4jConfig(
                   @Value("${security.tokenname}") tokenName: String,
                   securityUtils: SecurityUtils
                 ) {

  @Bean
  def config: Config = {
    val anonymmous = new AnonymousClient()
    val baseAuth = new DirectBasicAuthClient(new SignInAuthenticator(securityUtils))
    val tokenClient = new CookieClient(tokenName, new SessionAuthenticator(securityUtils))
    val config = new Config(anonymmous, baseAuth, tokenClient)

    config.addAuthorizer(UserRoleEnum.USER.name, new RequireAnyRoleAuthorizer(UserRoleEnum.USER.name))
    config.addAuthorizer(UserRoleEnum.ADMIN.name, new RequireAnyRoleAuthorizer(UserRoleEnum.ADMIN.name))
    config
  }
}

object Pac4jConfig {

  def loadProfile(user: UserWithRole): CommonProfile = {
    val profile = new CommonProfile
    profile.addRole(user.getRole.name)
    profile.addAttribute("user", user)
    profile
  }
}
