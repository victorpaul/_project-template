package com.projecttemplategroup.projecttemplateartifact

import java.util.Base64

import com.projecttemplategroup.projecttemplateartifact.security.Pac4jConfig
import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.skyscreamer.jsonassert.JSONAssert.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.test.context.{ContextConfiguration, TestContextManager, TestPropertySource}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = Array("classpath:application-test.properties"))
@ContextConfiguration(classes = Array(classOf[ProjecttemplateartifactApplication]))
abstract class BaseSpringTest extends AnyFeatureSpecLike with Matchers {

  @Autowired
  var restTemplate: TestRestTemplate = _
  new TestContextManager(this.getClass).prepareTestInstance(this)

  def baseAuthHeader(username: String, password: String): HttpHeaders = {
    val auth = Base64.getEncoder.encodeToString((username + ":" + password).getBytes)
    val headers = new HttpHeaders
    headers.add("Authorization", "Basic " + auth)
    headers
  }

  def cookieHeader(token: String): HttpHeaders = {
    val headers = new HttpHeaders
    headers.add(HttpHeaders.COOKIE, String.format("%s=%s; Max-Age=28800;HttpOnly", Pac4jConfig.HEADER_TOKEN_NAME, token))
    headers
  }

  def jsonAssert(expected: String, actual: String, strict: Boolean): Unit = {
    assertEquals(expected, actual, strict)
  }
}
