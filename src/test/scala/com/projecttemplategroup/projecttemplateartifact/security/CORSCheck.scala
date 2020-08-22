package com.projecttemplategroup.projecttemplateartifact.security

import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest
import org.springframework.http.{HttpEntity, HttpHeaders, HttpMethod, HttpStatus}
import scala.jdk.CollectionConverters._

class CORSCheck extends BaseSpringTest {

  Feature("check cors headers") {

    Scenario("success get cors headers on OPTION") {
      val resp = restTemplate.exchange("/ping", HttpMethod.OPTIONS, new HttpEntity[String](new HttpHeaders), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe null

      Seq(
        ("Access-Control-Allow-Credentials", Seq("true")),
        ("Access-Control-Allow-Methods", Seq("GET,POST,PUT,DELETE,HEAD")),
        ("Access-Control-Allow-Headers", Seq(s"Authorization,TOKEN,Origin,X-Requested-With,Content-Type,Accept"))
      ).foreach(kv => {
        resp.getHeaders.get(kv._1).asScala.toSeq shouldBe kv._2
      });
    }

    Scenario("success get cors headers on GET") {
      val resp = restTemplate.exchange("/ping", HttpMethod.GET, new HttpEntity[String](new HttpHeaders), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "pong"

      Seq(
        ("Access-Control-Allow-Credentials", Seq("true")),
        ("Referrer-Policy", Seq("origin-when-cross-origin")),
        ("X-Frame-Options", Seq("sameorigin")),
        ("Strict-Transport-Security", Seq("max-age=31536000; includeSubDomains")),
        ("X-XSS-Protection", Seq("1; mode=block")),
        ("Content-Security-Policy-Report-Only", Seq("default-src 'self';")),
        ("WWW-Authenticate", Seq(""))
      ).foreach(kv => {
        resp.getHeaders.get(kv._1).asScala.toSeq shouldBe kv._2
      });
    }

  }

}
