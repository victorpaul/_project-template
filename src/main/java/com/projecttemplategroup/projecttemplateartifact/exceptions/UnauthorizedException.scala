package com.projecttemplategroup.projecttemplateartifact.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class UnauthorizedException(message: String = "Unauthorized") extends RuntimeException {
  override def getMessage: String = message
}
