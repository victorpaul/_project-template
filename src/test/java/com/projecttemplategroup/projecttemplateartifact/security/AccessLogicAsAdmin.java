package com.projecttemplategroup.projecttemplateartifact.security;

import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessLogicAsAdmin extends BaseSpringTest {

    @Test
    void success_ping_public_api_as_admin() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("test-token-admin")), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("public api pong", resp.getBody());
    }

    @Test
    void success_ping_admin_api_as_admin() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1-adm/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("test-token-admin")), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("admin pong", resp.getBody());
    }

    @Test
    void fail_ping_admin_api_with_invalid_token() {
        ResponseEntity<Map> resp = restTemplate.exchange("/v1-adm/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("aaa")), Map.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
        assertEquals("", resp.getBody().get("message"));
    }

    @Test
    void fail_ping_user_api_as_admin() {
        ResponseEntity<Map> resp = restTemplate.exchange("/v1-usr/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("test-token-admin")), Map.class);

        assertEquals(HttpStatus.FORBIDDEN, resp.getStatusCode());
        assertEquals("", resp.getBody().get("message"));
    }


}
