package com.projecttemplategroup.projecttemplateartifact.security

import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest
import org.springframework.http.{HttpEntity, HttpMethod, HttpStatus, ResponseEntity}

class AccessLogicAsAdmin extends BaseSpringTest {

  val adminToken = "test-token-admin"

  Feature("check admin cookie token") {

    Scenario("success call public api as admin") {
      val resp = restTemplate.exchange("/v1/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader(adminToken)), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "public api pong"
    }

    Scenario("success call admin api as admin") {
      val resp: ResponseEntity[String] = restTemplate.exchange("/v1-adm/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader(adminToken)), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "admin pong"
    }

    Scenario("fail to call admin api with invalid token") {
      val resp = restTemplate.exchange("/v1-adm/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader("aaa")), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.UNAUTHORIZED
      jsonAssert(
        """{
          |"status":401,
          |"error":"Unauthorized",
          |"message":""}""".stripMargin, resp.getBody, false)
    }

    Scenario("fail to call user api as admin") {
      val resp = restTemplate.exchange("/v1-usr/ping", HttpMethod.GET, new HttpEntity[String](cookieHeader(adminToken)), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.FORBIDDEN
      jsonAssert(
        """{
          |"status":403,
          |"error":"Forbidden",
          |"message":""}""".stripMargin, resp.getBody, false)
    }

  }
}
