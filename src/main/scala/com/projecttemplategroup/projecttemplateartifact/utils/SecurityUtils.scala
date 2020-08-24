package com.projecttemplategroup.projecttemplateartifact.utils

import com.projecttemplategroup.projecttemplateartifact.LoggedInDemoUser
import com.projecttemplategroup.projecttemplateartifact.interfaces.UserWithRole
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator
import org.springframework.stereotype.{Service}

trait SecurityUtils {

  def getUserByCredentials(username: String, password: String): Option[UserWithRole]

  def getUserByToken(token: String): Option[UserWithRole]

}

@Service
class SecurityUtilsImpl extends SecurityUtils {

  override def getUserByCredentials(username: String, password: String): Option[UserWithRole] = {

    // todo, its demo sign in logic, use your own implementation with DB
    (new EmailValidator().isValid(username, null), password == "qwerty") match {
      case (true, true) => Some(new LoggedInDemoUser(username))
      case _ => None
    }

  }

  override def getUserByToken(token: String): Option[UserWithRole] = {
    // todo, its demo token validation, use your own implementation with DB
    if (token.contains("test-token-")) Some(new LoggedInDemoUser(token))
    else None
  }
}
