package com.projecttemplategroup.projecttemplateartifact.security.authenticators

import com.projecttemplategroup.projecttemplateartifact.exceptions.UnauthorizedException
import com.projecttemplategroup.projecttemplateartifact.security.{Pac4jConfig}
import com.projecttemplategroup.projecttemplateartifact.utils.SecurityUtils
import org.pac4j.core.context.WebContext
import org.pac4j.core.credentials.UsernamePasswordCredentials
import org.pac4j.core.credentials.authenticator.Authenticator

class SignInAuthenticator(var securityUtils: SecurityUtils) extends Authenticator[UsernamePasswordCredentials] {
  private def throwsException(message: String): Unit = {
    throw new UnauthorizedException(message)
  }

  override def validate(credentials: UsernamePasswordCredentials, context: WebContext): Unit = {
    if (credentials == null) throwsException("No credential")
    val username = credentials.getUsername
    val password = credentials.getPassword

    securityUtils.getUserByCredentials(username, password) match {
      case Some(user) => credentials.setUserProfile(Pac4jConfig.loadProfile(user))
      case _ => throwsException("Sign in failed")
    }
  }
}

