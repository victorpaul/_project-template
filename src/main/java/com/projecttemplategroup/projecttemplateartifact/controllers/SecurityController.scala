package com.projecttemplategroup.projecttemplateartifact.controllers

import com.projecttemplategroup.projecttemplateartifact.security.UserWithRole
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.pac4j.core.context.{JEEContext}
import org.pac4j.core.profile.{CommonProfile, ProfileManager}

abstract class SecurityController {

  def getSignedInProfile(implicit request: HttpServletRequest, response: HttpServletResponse): UserWithRole = {
    val webContext = new JEEContext(request, response)
    val manager = new ProfileManager[CommonProfile](webContext)
    manager.get(false).get.getAttribute("user", classOf[UserWithRole])
  }
}
