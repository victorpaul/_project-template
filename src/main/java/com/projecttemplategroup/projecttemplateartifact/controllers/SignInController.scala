package com.projecttemplategroup.projecttemplateartifact.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class SignInController extends SecurityController {

  @GetMapping(Array("/v1/signin"))
  def signIn(implicit request: HttpServletRequest, response: HttpServletResponse): String = {
    getSignedInProfile.toString
  }

}
