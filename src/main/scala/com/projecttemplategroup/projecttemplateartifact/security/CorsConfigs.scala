package com.projecttemplategroup.projecttemplateartifact.security

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.{HandlerInterceptor}

class CorsConfigs(allowedHeader: Seq[String]) extends HandlerInterceptor {

  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean = {
    val allowedOrigin = request.getHeader("Origin")
    val allowedCredentials = "true"

    response.setHeader("Access-Control-Allow-Origin", allowedOrigin)
    response.setHeader("Access-Control-Allow-Credentials", allowedCredentials)

    if (request.getMethod.toUpperCase() == "OPTIONS") {
      response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,HEAD")
      response.setHeader("Access-Control-Allow-Headers", s"Authorization,${allowedHeader.mkString(",")},Origin,X-Requested-With,Content-Type,Accept")
      response.setStatus(HttpStatus.OK.value())
    } else {
      response.setHeader("Referrer-Policy", "origin-when-cross-origin")
      response.setHeader("X-Frame-Options", "sameorigin")
      response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains")
      response.setHeader("X-XSS-Protection", "1; mode=block")
      response.setHeader("Content-Security-Policy-Report-Only", "default-src 'self';")
    }

    request.getMethod.toUpperCase() != "OPTIONS"
  }

}
