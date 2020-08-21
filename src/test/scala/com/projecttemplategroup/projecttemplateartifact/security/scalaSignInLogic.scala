package com.projecttemplategroup.projecttemplateartifact.security

import com.projecttemplategroup.projecttemplateartifact.BaseSpringFeatureTest

import org.springframework.http.{HttpEntity, HttpMethod, HttpStatus}

class scalaSignInLogic extends BaseSpringFeatureTest {

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

}
