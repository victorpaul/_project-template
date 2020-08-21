package com.projecttemplategroup.projecttemplateartifact.security.authenticators

import com.projecttemplategroup.projecttemplateartifact.security.{Pac4jConfig, UserWithRole}
import com.projecttemplategroup.projecttemplateartifact.utils.SecurityUtils
import org.pac4j.core.context.WebContext
import org.pac4j.core.credentials.TokenCredentials
import org.pac4j.core.credentials.authenticator.Authenticator
import org.pac4j.core.exception.CredentialsException
import org.pac4j.core.util.CommonHelper

class SessionAuthenticator(var securityUtils: SecurityUtils) extends Authenticator[TokenCredentials] {
  private def throwsException(message: String): Unit = {
    throw new CredentialsException(message)
  }

  override def validate(credentials: TokenCredentials, context: WebContext): Unit = {
    if (credentials == null) throwsException("Credentials must not be null")
    if (CommonHelper.isBlank(credentials.getToken)) throwsException("Token must not be blank")

    securityUtils.getUserByToken(credentials.getToken) match {
      case Some(user) => credentials.setUserProfile(Pac4jConfig.loadProfile(user))
      case _ => throwsException("Sign in failed")
    }
  }
}
