package com.projecttemplategroup.projecttemplateartifact.security

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.{HandlerInterceptor}

class CorsConfigs(allowedHeader: Seq[String]) extends HandlerInterceptor {

  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean = {
    val allowedOrigin = request.getHeader("Origin")

    response.setHeader("Access-Control-Allow-Origin", allowedOrigin)
    response.setHeader("Access-Control-Allow-Credentials", "TRUE")
    response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE")
    response.setHeader("Access-Control-Allow-Headers", s"Authorization,${allowedHeader.mkString(",")},Origin,X-Requested-With,Content-Type,Accept")

    if (request.getMethod.toUpperCase() == "OPTIONS") {
      response.setStatus(HttpStatus.OK.value())
      return false
    }

    true
  }

}
