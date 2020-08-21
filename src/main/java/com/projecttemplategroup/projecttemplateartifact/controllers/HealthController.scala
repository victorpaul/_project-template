package com.projecttemplategroup.projecttemplateartifact.controllers

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class HealthController {

  @GetMapping(Array("/ping")) def ping = "pong"

  @GetMapping(Array("/v1/ping")) def publicApi = "public api pong"

  @GetMapping(Array("/v1-usr/ping")) def userApi = "user pong"

  @GetMapping(Array("/v1-adm/ping")) def adminApi = "admin pong"

  @GetMapping(Array("/v1-sup/ping")) def superApi = "super pong"

}
