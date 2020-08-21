package com.projecttemplategroup.projecttemplateartifact.security

import org.springframework.http.{HttpEntity, HttpMethod, HttpStatus, ResponseEntity}
import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest

class AccessLogicAsUser extends BaseSpringTest {

  val userToken = "test-token-user"

  Feature("check user cookie token") {

    Scenario("success call public api as user") {
      val resp = restTemplate.exchange("/v1/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader(userToken)), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "public api pong"
    }

    Scenario("success call user api as user") {
      val resp: ResponseEntity[String] = restTemplate.exchange("/v1-usr/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader(userToken)), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "user pong"
    }

    Scenario("fail to call user api with invalid token") {
      val resp = restTemplate.exchange("/v1-usr/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader("aaa")), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.UNAUTHORIZED
      jsonAssert(
        """{
          |"status":401,
          |"error":"Unauthorized",
          |"message":""}""".stripMargin, resp.getBody, false)
    }

    Scenario("fail to call admin api as user") {
      val resp = restTemplate.exchange("/v1-adm/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader(userToken)), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.FORBIDDEN
      jsonAssert(
        """{
          |"status":403,
          |"error":"Forbidden",
          |"message":""}""".stripMargin, resp.getBody, false)
    }

  }
}
