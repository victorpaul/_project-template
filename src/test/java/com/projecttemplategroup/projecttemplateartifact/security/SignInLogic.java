package com.projecttemplategroup.projecttemplateartifact.security;

import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
//todo move to scala
class SignInLogic extends BaseSpringTest {

    @Test
    void success_ping() {
        ResponseEntity<String> resp = restTemplate.exchange("/ping", HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("pong", resp.getBody());
    }

    @Test
    void success_user_sign_in() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity<String>(baseAuthHeader("user@user.com", "qwerty")), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("LoggedInDemoUser{id=3, role=USER}", resp.getBody());
    }

    @Test
    void fail_user_sign_in() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity<String>(baseAuthHeader("user@user.com", "azerty")), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
    }

    @Test
    void success_admin_sign_in() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity<String>(baseAuthHeader("admin@admin.com", "qwerty")), String.class);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("LoggedInDemoUser{id=2, role=ADMIN}", resp.getBody());
    }

    @Test
    void fail_admin_sign_in() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity<String>(baseAuthHeader("admin@admin.com", "azerty")), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
    }

    @Test
    void fail_super_sign_in() {
        ResponseEntity<String> resp = restTemplate.exchange("/v1/signin", HttpMethod.GET, new HttpEntity<String>(baseAuthHeader("super@super.com", "azerty")), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());

    }

}
