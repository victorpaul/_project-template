package com.projecttemplategroup.projecttemplateartifact.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//todo move to scala
@RestController
public class CommonController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/v1/ping")
    public String publicApi() {
        return "public api pong";
    }

    @GetMapping("/v1-usr/ping")
    public String userApi() {
        return "user pong";
    }

    @GetMapping("/v1-adm/ping")
    public String adminApi() {
        return "admin pong";
    }

    @GetMapping("/v1-sup/ping")
    public String superApi() {
        return "super pong";
    }
}
