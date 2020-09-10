package com.projecttemplategroup.projecttemplateartifact

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(exclude = Array(classOf[SecurityAutoConfiguration])) // we do not need default spring boot security as we prefer to use pac4j
class ProjecttemplateartifactApplication

object ProjecttemplateartifactApplication extends App {

  SpringApplication.run(classOf[ProjecttemplateartifactApplication])

}
