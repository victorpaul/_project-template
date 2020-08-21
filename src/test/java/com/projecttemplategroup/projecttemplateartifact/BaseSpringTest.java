package com.projecttemplategroup.projecttemplateartifact;

import com.projecttemplategroup.projecttemplateartifact.security.Pac4jConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
abstract public class BaseSpringTest {

    @LocalServerPort
    private int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected HttpHeaders baseAuthHeader(String username, String password) {
        String auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + auth);
        return headers;
    }

    protected HttpHeaders cookieHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(Pac4jConfig.HEADER_TOKEN_NAME, token);
        headers.add(HttpHeaders.COOKIE,String.format("%s=%s; Max-Age=28800; Expires=Fri, 14-Jun-2019 23:17:59 GMT; HttpOnly",Pac4jConfig.HEADER_TOKEN_NAME, token));
        return headers;
    }

}
