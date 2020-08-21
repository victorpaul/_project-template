package com.projecttemplategroup.projecttemplateartifact.security;

import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
//todo move to scala
class AccessLogicAsUser extends BaseSpringTest {

    @Test
    void success_ping_public_api_as_user() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("test-token-user")), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("public api pong", resp.getBody());
    }

    @Test
    void success_ping_user_api_as_user() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1-usr/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("test-token-user")), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("user pong", resp.getBody());
    }

    @Test
    void fail_ping_user_api_with_invalid_token() {
        ResponseEntity<Map> resp = restTemplate.exchange("/v1-usr/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("aaa")), Map.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
        assertEquals("", resp.getBody().get("message"));
    }

    @Test
    void fail_ping_admin_api_as_user() {
        ResponseEntity<Map> resp = restTemplate.exchange("/v1-adm/ping", HttpMethod.GET, new HttpEntity<String>(cookieHeader("test-token-user")), Map.class);

        assertEquals(HttpStatus.FORBIDDEN, resp.getStatusCode());
        assertEquals("", resp.getBody().get("message"));
    }

}
