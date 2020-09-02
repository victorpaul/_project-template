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
      val headers = resp.getHeaders
      Seq(
        ("Access-Control-Allow-Credentials", Seq("TRUE")),
        ("Access-Control-Allow-Methods", Seq("GET,POST,PUT,DELETE")),
        ("Access-Control-Allow-Headers", Seq(s"Authorization,TOKEN,Origin,X-Requested-With,Content-Type,Accept"))
      ).foreach(kv => {
        resp.getHeaders.get(kv._1).asScala.toSeq shouldBe kv._2
      });
    }

    Scenario("success get cors headers on GET") {
      val resp = restTemplate.exchange("/ping", HttpMethod.GET, new HttpEntity[String](new HttpHeaders), classOf[String])

      resp.getStatusCode shouldBe HttpStatus.OK
      resp.getBody shouldBe "pong"

      val headers = resp.getHeaders
      Seq(
        ("Access-Control-Allow-Credentials", Seq("TRUE")),
        ("Access-Control-Allow-Methods", Seq("GET,POST,PUT,DELETE")),
        ("Access-Control-Allow-Headers", Seq(s"Authorization,TOKEN,Origin,X-Requested-With,Content-Type,Accept"))
      ).foreach(kv => {
        resp.getHeaders.get(kv._1).asScala.toSeq shouldBe kv._2
      });
    }

  }

}
