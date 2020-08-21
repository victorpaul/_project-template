package com.projecttemplategroup.projecttemplateartifact.security

import org.springframework.http.{HttpEntity, HttpHeaders, HttpMethod, HttpStatus}
import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest

class SignInLogic extends BaseSpringTest {

  Feature("anybody can ping"){

    Scenario("success ping by anonymous"){
      val resp = restTemplate.exchange("/ping", HttpMethod.GET, new HttpEntity[String](new HttpHeaders), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "pong"
    }

  }

  Feature("Check user signing in") {

    Scenario("success sign in") {
      val resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity[String](baseAuthHeader("user@user.com", "qwerty")), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "LoggedInDemoUser{id=3, role=USER}"
    }

    Scenario("fail sign in") {
      val resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity[String](baseAuthHeader("user@user.com", "azerty")), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.UNAUTHORIZED
      jsonAssert(
        """{
          |"status":401,
          |"error":"Unauthorized",
          |"message":""}""".stripMargin, resp.getBody, false);
    }
  }

  Feature("Check admin signing in") {

    Scenario("success sign in") {
      val resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity[String](baseAuthHeader("admin@admin.com", "qwerty")), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "LoggedInDemoUser{id=2, role=ADMIN}"
    }

    Scenario("fail sign in") {
      val resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity[String](baseAuthHeader("admin@admin.com", "azerty")), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.UNAUTHORIZED
      jsonAssert(
        """{
          |"status":401,
          |"error":"Unauthorized",
          |"message":""}""".stripMargin, resp.getBody, false);
    }
  }

}
